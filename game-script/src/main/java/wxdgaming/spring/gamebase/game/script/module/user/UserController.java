package wxdgaming.spring.gamebase.game.script.module.user;

import io.netty.handler.codec.http.HttpHeaderNames;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-11 20:17
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @ResponseBody
    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        response.addCookie(new Cookie(HttpHeaderNames.AUTHORIZATION.toString(), "wxd"));

    }

}
