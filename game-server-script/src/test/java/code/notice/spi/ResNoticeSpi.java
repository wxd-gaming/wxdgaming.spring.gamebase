package code.notice.spi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import wxdgaming.spring.boot.net.ProtoMapping;
import wxdgaming.spring.boot.net.SocketSession;
import wxdgaming.spring.game.server.proto.notice.ResNotice;

/**
 * 
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: v1.1
 **/
@Slf4j
@Component
public class ResNoticeSpi {

    @ProtoMapping
    public void ResNotice(SocketSession socketSession, ResNotice req) {

    }

}