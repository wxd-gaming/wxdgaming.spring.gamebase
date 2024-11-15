package wxdgaming.spring.gamebase.game.server.drive.decode;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.net.BootstrapBuilder;
import wxdgaming.spring.boot.net.MessageDispatcher;
import wxdgaming.spring.boot.net.SocketSession;
import wxdgaming.spring.boot.net.server.ServerMessageDecode;
import wxdgaming.spring.boot.rpc.RequestRpcMessageController;

/**
 * 实现websocket解码器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-11-13 21:10
 **/
@Slf4j
@Getter
@Setter
@Service
@ChannelHandler.Sharable
public class WebSocketMessageDecode extends ServerMessageDecode implements InitPrint {

    @Value("${socket.rpc-token}")
    private final String RPC_TOKEN = "getg6jhkopw435dvmkmcvx5y63-40";

    final RequestRpcMessageController requestRpcMessageController;

    public WebSocketMessageDecode(BootstrapBuilder bootstrapBuilder, MessageDispatcher dispatcher, RequestRpcMessageController requestRpcMessageController) {
        super(bootstrapBuilder, dispatcher);
        this.requestRpcMessageController = requestRpcMessageController;
    }

    @Override protected void action(SocketSession socketSession, String message) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(message);
        String token = jsonObject.getString("token");
        String cmd = jsonObject.getString("cmd");
        String params = jsonObject.getString("data");
        Object invoke = requestRpcMessageController.rpcReqSocketAction(socketSession, RPC_TOKEN, 0, cmd, params);
        log.info("action:{}", message);
    }
}
