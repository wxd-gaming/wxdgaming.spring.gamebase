package wxdgaming.spring.gamebase.background.module.game.controller;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.core.threading.ThreadContext;
import wxdgaming.spring.boot.core.timer.MyClock;
import wxdgaming.spring.boot.core.util.StringsUtil;
import wxdgaming.spring.boot.web.service.ResponseService;
import wxdgaming.spring.gamebase.background.CheckSign;
import wxdgaming.spring.gamebase.background.entity.bean.Account;
import wxdgaming.spring.gamebase.background.entity.bean.GameInfo;
import wxdgaming.spring.gamebase.background.module.game.GameInfoService;
import wxdgaming.spring.gamebase.background.module.server.ServerInfoService;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 服务器控制器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-14 13:56
 **/
@Slf4j
@Controller
@RequestMapping("/gameinfo")
public class GameInfoController {

    @Autowired GameInfoService gameInfoService;
    @Autowired ServerInfoService serverInfoService;
    @Autowired ResponseService responseService;

    @CheckSign()
    @ResponseBody()
    @RequestMapping("/list")
    public RunResult list(HttpServletRequest request,
                          @RequestParam(name = "search", required = false) String search) {
        Stream<GameInfo> stream = gameInfoService.list().stream();

        Account loginAccount = ThreadContext.context(Account.class);
        if (!loginAccount.isRoot()) {
            stream = stream.filter(gameInfo -> loginAccount.getGames().contains(gameInfo.getUid()));
        }

        if (StringsUtil.notEmptyOrNull(search)) {
            stream = stream.filter(gameInfo -> gameInfo.getUid().toString().equals(search) || gameInfo.getName().contains(search));
        }

        List<JSONObject> list = stream.map(gameInfo -> {
            JSONObject json = new JSONObject();
            json.put("uid", gameInfo.getUid());
            json.put("name", gameInfo.getName());
            json.put("icon", gameInfo.getIcon());
            json.put("description", gameInfo.getDescription());
            json.put("createdTime", MyClock.formatDate(gameInfo.getCreatedTime()));
            json.put("appKey", gameInfo.getAppKey());
            json.put("rechargeKey", gameInfo.getRechargeKey());
            return json;
        }).toList();
        return RunResult.ok().data(list);
    }

    @ResponseBody
    @RequestMapping("/list/platform")
    public RunResult list_platform(HttpServletRequest request,
                                   @RequestParam(name = "gameId") int gameId) {

        Account loginAccount = ThreadContext.context(Account.class);
        Optional<Collection<String>> strings = serverInfoService.listPlatforms(loginAccount, gameId);
        if (strings.isEmpty()) {
            return RunResult.error("权限不足");
        }
        return RunResult.ok().data(strings.get());
    }

    @CheckSign(isRoot = true)
    @ResponseBody
    @RequestMapping("/update/key/{key_name}")
    public RunResult updateAppKey(HttpServletRequest request,
                                  @RequestParam(name = "gameId") int gameId,
                                  @PathVariable("key_name") String key_name) {
        Account loginAccount = ThreadContext.context(Account.class);
        GameInfo gameInfo = gameInfoService.get(gameId);
        if (gameInfo == null) {
            return RunResult.error("id异常");
        }
        if (Objects.equals("app", key_name)) {
            gameInfo.setAppKey(StringsUtil.getRandomString(32));
        } else if (Objects.equals("recharge", key_name)) {
            gameInfo.setRechargeKey(StringsUtil.getRandomString(32));
        } else {
            return RunResult.error("参数异常");
        }
        log.warn("{} 修改 {} {} key", loginAccount, gameInfo, key_name);
        gameInfoService.save(gameInfo);
        return RunResult.ok().data(gameInfoService.list());
    }

    @CheckSign(isRoot = true)
    @ResponseBody
    @RequestMapping("/push")
    public RunResult push(HttpServletRequest request,
                          @RequestBody GameInfo gameInfo) {
        if (gameInfo.getCreatedTime() == 0)
            gameInfo.setCreatedTime(MyClock.millis());
        gameInfo.setUpdateTime(MyClock.millis());
        if (gameInfo.getUid() == null || gameInfo.getUid() == 0) {
            gameInfo.setUid(null);
        }
        if (StringsUtil.emptyOrNull(gameInfo.getAppKey())) {
            gameInfo.setAppKey(StringsUtil.getRandomString(32));
        }
        if (StringsUtil.emptyOrNull(gameInfo.getRechargeKey())) {
            gameInfo.setRechargeKey(StringsUtil.getRandomString(32));
        }
        gameInfoService.save(gameInfo);
        return RunResult.ok().data(gameInfoService.list());
    }

}
