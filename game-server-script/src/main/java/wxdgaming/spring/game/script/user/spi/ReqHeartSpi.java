package wxdgaming.spring.game.script.user.spi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import wxdgaming.spring.boot.core.timer.MyClock;
import wxdgaming.spring.boot.net.ProtoMapping;
import wxdgaming.spring.boot.net.SocketSession;
import wxdgaming.spring.game.server.proto.user.ReqHeart;
import wxdgaming.spring.game.server.proto.user.ResHeart;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: v1.1
 **/
@Slf4j
@Component
public class ReqHeartSpi {

    @ProtoMapping
    public void ReqLogin(SocketSession socketSession, ReqHeart req) {

        ResHeart resHeart = new ResHeart().setMilli(MyClock.millis());
        socketSession.writeAndFlush(resHeart);

    }

}