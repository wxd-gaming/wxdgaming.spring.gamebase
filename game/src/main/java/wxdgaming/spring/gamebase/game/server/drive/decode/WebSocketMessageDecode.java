package wxdgaming.spring.gamebase.game.server.drive.decode;

import io.netty.channel.ChannelHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.net.BootstrapBuilder;
import wxdgaming.spring.boot.net.MessageDispatcher;
import wxdgaming.spring.boot.net.SocketSession;
import wxdgaming.spring.boot.net.server.ServerMessageDecode;

/**
 * 实现websocket解码器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-11-13 21:10
 **/
@Slf4j
@Service
@ChannelHandler.Sharable
public class WebSocketMessageDecode extends ServerMessageDecode implements InitPrint {

    public WebSocketMessageDecode(BootstrapBuilder bootstrapBuilder, MessageDispatcher dispatcher) {
        super(bootstrapBuilder, dispatcher);
    }

    @Override protected void action(SocketSession socketSession, String message) throws Exception {
        super.action(socketSession, message);

    }
}
