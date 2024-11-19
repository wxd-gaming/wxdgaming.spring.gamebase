package wxdgaming.spring.gamebase.game.server.drive.decode;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.netty.channel.ChannelHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.threading.ThreadContext;
import wxdgaming.spring.boot.core.util.JwtUtils;
import wxdgaming.spring.boot.net.BootstrapBuilder;
import wxdgaming.spring.boot.net.MessageDispatcher;
import wxdgaming.spring.boot.net.SocketSession;
import wxdgaming.spring.boot.net.server.ServerMessageDecode;
import wxdgaming.spring.boot.rpc.RequestRpcMessageController;
import wxdgaming.spring.gamebase.game.server.bean.entity.user.Player;

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
    @Value("${login-jwt-Secret-Key}")
    private String PRIVATE_TOKEN = "ddddd";

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
        {
            Jws<Claims> claimsJws = JwtUtils.parseJWT(getPRIVATE_TOKEN(), token);
            String openId = claimsJws.getPayload().get("openId", String.class);
            /*注册线程变量*/
            ThreadContext.putContent(new Player().setUid(1L));
        }
        Object invoke = requestRpcMessageController.rpcReqSocketAction(socketSession, RPC_TOKEN, 0, cmd, params);
        log.info("action:{}", message);
    }
}
