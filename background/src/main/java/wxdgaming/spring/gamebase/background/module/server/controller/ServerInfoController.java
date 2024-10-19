package wxdgaming.spring.gamebase.background.module.server.controller;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wxdgaming.spring.boot.core.LocalShell;
import wxdgaming.spring.boot.core.format.TemplatePack;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.core.threading.ThreadContext;
import wxdgaming.spring.boot.core.timer.MyClock;
import wxdgaming.spring.boot.core.util.StringsUtil;
import wxdgaming.spring.gamebase.background.BackendService;
import wxdgaming.spring.gamebase.background.CheckSign;
import wxdgaming.spring.gamebase.background.entity.bean.Account;
import wxdgaming.spring.gamebase.background.entity.bean.ServerInfo;
import wxdgaming.spring.gamebase.background.module.game.GameInfoService;
import wxdgaming.spring.gamebase.background.module.server.ServerInfoService;

import java.io.File;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 服务器控制器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-14 13:56
 **/
@CheckSign
@RestController
@RequestMapping("/server")
public class ServerInfoController {

    @Autowired private BackendService backendService;
    @Autowired private GameInfoService gameInfoService;
    @Autowired private ServerInfoService serverInfoService;

    final TemplatePack templatePack = TemplatePack.build(this.getClass().getClassLoader(), "template/shell");

    @RequestMapping("/list/group")
    @ResponseBody
    public RunResult listGroup(HttpServletRequest request,
                               @RequestParam(name = "gameId") Integer gameId) {
        return RunResult.ok().data(serverInfoService.groupedByPlatform(gameId));
    }

    @RequestMapping("/list/platform")
    @ResponseBody
    public RunResult listByPlatform(HttpServletRequest request,
                                    @RequestParam(name = "gameId") Integer gameId,
                                    @RequestParam(name = "platform") String platform,
                                    @RequestParam(name = "search", required = false) String search) {
        Account loginAccount = ThreadContext.context(Account.class);
        Stream<ServerInfo> serverInfoStream = serverInfoService.streamAll(gameId, platform);

        if (StringsUtil.notEmptyOrNull(search)) {
            serverInfoStream = serverInfoStream
                    .filter(serverInfo ->
                            Objects.equals(String.valueOf(serverInfo.getSid()), search)
                            || serverInfo.getName().contains(search)
                            || serverInfo.getShowName().contains(search)
                    );
        }
        return RunResult.ok().data(serverInfoStream.toList());
    }

    @RequestMapping("/push")
    @ResponseBody
    public RunResult push(HttpServletRequest request, @RequestBody ServerInfo serverInfo) {
        if (serverInfo.getCreatedTime() == 0)
            serverInfo.setCreatedTime(MyClock.millis());
        serverInfo.setUpdateTime(MyClock.millis());

        ServerInfo oldServerInfo = serverInfoService.get(serverInfo.getGameId(), serverInfo.getPlatform(), serverInfo.getSid());
        if (oldServerInfo == null) {
            serverInfo.setUid(backendService.getGlobalData().getServerId().newId());
            serverInfoService.getServerInfoRepository().saveAndFlush(serverInfo);
        } else {
            oldServerInfo.setName(serverInfo.getName());
            oldServerInfo.setShowName(serverInfo.getShowName());
            oldServerInfo.setUpdateTime(serverInfo.getUpdateTime());
            serverInfoService.getServerInfoRepository().saveAndFlush(oldServerInfo);
        }

        return RunResult.ok().data(serverInfoService.list(serverInfo.getGameId()));
    }

    @RequestMapping("/operate")
    @ResponseBody
    public RunResult operate(HttpServletRequest request,
                             @RequestParam(name = "gameId") Integer gameId,
                             @RequestParam(name = "platform") String platform,
                             @RequestParam(name = "sid") Integer sid,
                             @RequestParam(name = "operate") String operate) throws Exception {
        ServerInfo serverInfo = serverInfoService.get(gameId, platform, sid);

        JSONObject json = new JSONObject();
        json.put("gameId", gameId);
        json.put("platform", platform);
        json.put("sid", sid);
        json.put("targetIp", serverInfo.getLan());
        json.put("version", "v10.001");

        String outFile = "target/out/shell/" + System.nanoTime() + ".sh";
        templatePack.ftl2File("rsync.ftl", json, outFile);
        // File file = new File(outFile);
        // LocalShell build = LocalShell.build(true, file.getParentFile());
        // build.putCmd("bash " + file.getName());
        // build.exit();
        return RunResult.ok();
    }

}
