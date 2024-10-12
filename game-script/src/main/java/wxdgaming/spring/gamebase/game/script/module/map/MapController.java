package wxdgaming.spring.gamebase.game.script.module.map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.threading.ThreadContext;
import wxdgaming.spring.gamebase.game.server.bean.entity.user.Player;

/**
 * 地图接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-12 09:12
 **/
@Slf4j
@Controller
@RequestMapping("/game/map")
public class MapController implements InitPrint {

    @ResponseBody
    @RequestMapping("/index")
    public String index() {

        Player player = ThreadContext.context(Player.class);
        System.out.println(player);
        return "map/index";
    }

}
