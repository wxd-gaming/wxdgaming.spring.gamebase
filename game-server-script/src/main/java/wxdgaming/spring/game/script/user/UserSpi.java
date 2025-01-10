package wxdgaming.spring.game.script.user;

import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.net.ProtoMapper;
import wxdgaming.spring.boot.net.SocketSession;
import wxdgaming.spring.game.server.proto.UserMessage;

/**
 * 用户接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-10 16:53
 **/
@Service
public class UserSpi implements InitPrint {

    @ProtoMapper
    public void login(SocketSession socketSession, UserMessage.ReqLogin reqLogin) {

    }

}
