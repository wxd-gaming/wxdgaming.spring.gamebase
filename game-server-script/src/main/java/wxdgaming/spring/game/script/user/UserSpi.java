package wxdgaming.spring.game.script.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.util.JwtUtils;
import wxdgaming.spring.boot.net.ProtoMapper;
import wxdgaming.spring.boot.net.SocketSession;
import wxdgaming.spring.game.server.bean.entity.user.PlayerSummary;
import wxdgaming.spring.game.server.module.user.UserService;
import wxdgaming.spring.game.server.proto.UserMessage;

import java.util.stream.Stream;

/**
 * 用户接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-10 16:53
 **/
@Slf4j
@Service
public class UserSpi implements InitPrint {

    final UserService userService;

    public UserSpi(UserService userService) {
        this.userService = userService;
    }

    @ProtoMapper
    public void login(SocketSession socketSession, UserMessage.ReqLogin reqLogin) {
        String token = reqLogin.getToken();
        Jws<Claims> claimsJws = JwtUtils.parseJWT(token);
        String openId = claimsJws.getPayload().get("openId", String.class);
        Stream<PlayerSummary> allPlayerSummary = userService.findAllPlayerSummary(openId);
        UserMessage.ResLogin resLogin = new UserMessage.ResLogin();
        allPlayerSummary.forEach(playerSummary -> {
            resLogin.getRoleList().add(new UserMessage.RoleBean().setRid(playerSummary.getUid()));
        });
        socketSession.writeAndFlush(resLogin);
        log.info("登陆完成：{}", openId);
    }

}
