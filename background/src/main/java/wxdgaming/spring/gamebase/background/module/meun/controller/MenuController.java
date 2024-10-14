package wxdgaming.spring.gamebase.background.module.meun.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.gamebase.background.module.game.GameInfoService;
import wxdgaming.spring.gamebase.background.module.meun.MenuService;
import wxdgaming.spring.gamebase.background.module.server.ServerInfoService;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-14 17:11
 **/
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired MenuService menuService;
    @Autowired GameInfoService gameInfoService;
    @Autowired ServerInfoService serverInfoService;

    @RequestMapping("/list")
    public RunResult list() {
        List<JSONObject> list = new ArrayList<>();
        gameInfoService.list().forEach(menu -> {
            JSONObject jo = new JSONObject();
            jo.put("uid", menu.getUid());
            jo.put("name", menu.getName());
            jo.put("icon", menu.getIcon());
            jo.put("description", menu.getDescription());
            jo.put("platforms", serverInfoService.listPlatforms(menu.getUid()));
            list.add(jo);
        });
        return RunResult.ok().data(list);
    }

}
