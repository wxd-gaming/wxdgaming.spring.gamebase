package wxdgaming.spring.gamebase.game.script.module.mail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wxdgaming.spring.boot.core.InitPrint;

/**
 * 测试接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-11 15:17
 **/
@Controller
@RequestMapping("/script/mail")
public class MailController implements InitPrint {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "ok";
    }

}
