package wxdgaming.spring.game.script.user.spi;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wxdgaming.spring.boot.core.util.JwtUtils;
import wxdgaming.spring.boot.net.ProtoMapping;
import wxdgaming.spring.boot.net.SocketSession;
import wxdgaming.spring.game.server.bean.entity.user.PlayerSummary;
import wxdgaming.spring.game.server.module.user.UserService;
import wxdgaming.spring.game.server.proto.user.ReqLogin;
import wxdgaming.spring.game.server.proto.user.ResLogin;
import wxdgaming.spring.game.server.proto.user.RoleBean;

import java.util.stream.Stream;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: v1.1
 **/
@Slf4j
@Component
public class ReqLoginSpi {

    final UserService userService;

    @Autowired
    public ReqLoginSpi(UserService userService) {
        this.userService = userService;
    }

    @ProtoMapping
    public void ReqLogin(SocketSession socketSession, ReqLogin req) {
        String token = req.getToken();
        Jws<Claims> claimsJws = JwtUtils.parseJWT(token);
        String openId = claimsJws.getPayload().get("openId", String.class);
        Stream<PlayerSummary> allPlayerSummary = userService.findAllPlayerSummary(openId);
        ResLogin resLogin = new ResLogin();
        allPlayerSummary.forEach(playerSummary -> {
            resLogin.getRoleList().add(new RoleBean().setRid(playerSummary.getUid()));
        });
        socketSession.writeAndFlush(resLogin);
        log.info("登录完成：{}", openId);
    }

}