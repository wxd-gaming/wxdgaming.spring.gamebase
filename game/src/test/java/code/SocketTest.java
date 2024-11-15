package code;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import wxdgaming.spring.boot.core.threading.DefaultExecutor;
import wxdgaming.spring.boot.core.threading.ExecutorBuilder;
import wxdgaming.spring.boot.net.*;
import wxdgaming.spring.boot.net.client.ClientMessageDecode;
import wxdgaming.spring.boot.net.client.ClientMessageEncode;
import wxdgaming.spring.boot.net.client.SocketClientBuilder;
import wxdgaming.spring.boot.net.client.WebSocketClient;

import java.nio.charset.StandardCharsets;

@Slf4j
public class SocketTest {

    BootstrapBuilder bootstrapBuilder;
    MessageDispatcher messageDispatcher;
    DefaultExecutor defaultExecutor;

    WebSocketClient webSocketClient;

    @Before
    public void before() throws Exception {
        defaultExecutor = new ExecutorBuilder().defaultExecutor();
        bootstrapBuilder = new BootstrapBuilder();
        messageDispatcher = new MessageDispatcher();


        SocketClientBuilder socketClientBuilder = new SocketClientBuilder();
        socketClientBuilder.setWeb(new SocketClientBuilder.Config().setPort(22001));
        socketClientBuilder.init();

        webSocketClient = socketClientBuilder.webSocketClient(
                defaultExecutor,
                bootstrapBuilder,
                new SessionHandler() {},
                new ClientMessageDecode(bootstrapBuilder, messageDispatcher),
                new ClientMessageEncode(messageDispatcher)
        );

        webSocketClient.init();

    }

    @Test
    public void t0() throws Exception {

        {
            webSocketClient.connect();
            System.in.read();
            SocketSession session = webSocketClient.idleSession();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cmd", "/user/login");
            jsonObject.put("token", "ddd");
            jsonObject.put("data", "{\"account\":\"wxd\",\"password\":\"123456\"}");
            session.writeAndFlush(jsonObject.toString());
        }
        // Thread.sleep(3000);
        System.in.read();
    }

    public void writeAndFlush(SessionGroup sessions, String message) {
        ByteBuf byteBuf = ByteBufUtil.pooledByteBuf(30);
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        byteBuf.writeInt(bytes.length + 4);
        byteBuf.writeInt(1);
        byteBuf.writeBytes(bytes);
        sessions.writeAndFlush(byteBuf);
    }

    public void write(SessionGroup channels, String message) {
        ByteBuf byteBuf = ByteBufUtil.pooledByteBuf(30);
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        byteBuf.writeInt(bytes.length + 4);
        byteBuf.writeInt(1);
        byteBuf.writeBytes(bytes);
        channels.write(byteBuf);
    }

}