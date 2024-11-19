package wxdgaming.spring.gamebase.game.script.module.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wxdgaming.spring.boot.core.threading.ThreadContext;
import wxdgaming.spring.boot.core.util.JwtUtils;
import wxdgaming.spring.boot.net.SocketSession;
import wxdgaming.spring.boot.rpc.RPC;
import wxdgaming.spring.gamebase.game.server.bean.entity.user.Player;
import wxdgaming.spring.gamebase.game.server.module.login.LoginService;

/**
 * 用户接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-11 20:17
 **/
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired LoginService loginService;

    @RPC(value = "/login", checkToken = false)
    public void login(SocketSession session, @RequestParam(value = "token") String token) {
        Player player = ThreadContext.context(Player.class);
        log.info("登录请求 {}", token);
        Jws<Claims> claimsJws = JwtUtils.parseJWT(loginService.getPRIVATE_TOKEN(), token);
        String openId = claimsJws.getPayload().get("openId", String.class);

    }

}
