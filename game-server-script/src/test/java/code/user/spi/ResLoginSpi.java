package code.user.spi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import wxdgaming.spring.boot.net.ProtoMapping;
import wxdgaming.spring.boot.net.SocketSession;
import wxdgaming.spring.game.server.proto.user.ResLogin;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: v1.1
 **/
@Slf4j
@Component
public class ResLoginSpi {

    @ProtoMapping
    public void ResLogin(SocketSession socketSession, ResLogin req) {
        log.info("登录完成");
    }

}