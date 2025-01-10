package wxdgaming.spring.game.script.gm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * gm http 接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-10 17:29
 **/
@RestController()
@RequestMapping("/gm")
public class GMController {

    @RequestMapping("/index")
    public String index() {
        return "gm";
    }

}
