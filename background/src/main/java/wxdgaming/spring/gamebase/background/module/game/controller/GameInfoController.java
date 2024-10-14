package wxdgaming.spring.gamebase.background.module.game.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.core.timer.MyClock;
import wxdgaming.spring.boot.web.service.ResponseService;
import wxdgaming.spring.gamebase.background.entity.bean.GameInfo;
import wxdgaming.spring.gamebase.background.module.game.GameInfoService;
import wxdgaming.spring.gamebase.background.module.server.ServerInfoService;

/**
 * 服务器控制器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-14 13:56
 **/
@Controller
@RequestMapping("/gameinfo")
public class GameInfoController {

    @Autowired GameInfoService gameInfoService;
    @Autowired ServerInfoService serverInfoService;
    @Autowired ResponseService responseService;

    @ResponseBody()
    @RequestMapping("/list")
    public RunResult list(HttpServletRequest request) {
        return RunResult.ok().data(gameInfoService.list());
    }

    @ResponseBody
    @RequestMapping("/list/platform")
    public RunResult list_platform(HttpServletRequest request,
                                   @RequestParam(name = "gameId", required = false) int gameId) {
        return RunResult.ok().data(serverInfoService.listPlatforms(gameId));
    }

    @ResponseBody
    @RequestMapping("/push")
    public RunResult push(HttpServletRequest request,
                          @RequestBody GameInfo gameInfo) {
        if (gameInfo.getCreatedTime() == 0)
            gameInfo.setCreatedTime(MyClock.millis());
        gameInfo.setUpdateTime(MyClock.millis());
        if (gameInfo.getUid() == null || gameInfo.getUid() == 0) {
            gameInfo.setUid(((int) gameInfoService.getGameInfoRepository().count()) + 1);
        }
        gameInfoService.getGameInfoRepository().save(gameInfo);
        gameInfoService.getGameInfoMap().put(gameInfo.getUid(), gameInfo);
        return RunResult.ok().data(gameInfoService.list());
    }

}
