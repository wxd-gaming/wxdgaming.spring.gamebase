package wxdgaming.spring.gamebase.client;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import wxdgaming.spring.boot.core.lang.RandomUtils;
import wxdgaming.spring.boot.core.threading.DefaultExecutor;
import wxdgaming.spring.boot.net.BootstrapBuilder;
import wxdgaming.spring.boot.net.SessionHandler;
import wxdgaming.spring.boot.net.client.ClientMessageDecode;
import wxdgaming.spring.boot.net.client.ClientMessageEncode;
import wxdgaming.spring.boot.net.client.SocketClientBuilder;
import wxdgaming.spring.boot.net.client.TcpSocketClient;
import wxdgaming.spring.boot.rpc.RpcService;

/**
 * login server client
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-07 11:18
 **/
@Getter
@Controller
@ConfigurationProperties("socket.client.login")
public class LoginClientBuild extends SocketClientBuilder.Config {

    @Autowired RpcService rpcService;
    TcpSocketClient tcpSocketClient = null;

    @Bean(name = "loginClient")
    @ConditionalOnProperty(prefix = "socket.client.login", name = "port")
    public TcpSocketClient loginClient(DefaultExecutor defaultExecutor, BootstrapBuilder bootstrapBuilder,
                                       SocketClientBuilder socketClientBuilder,
                                       SessionHandler sessionHandler,
                                       ClientMessageDecode clientMessageDecode,
                                       ClientMessageEncode clientMessageEncode) {

        tcpSocketClient = new TcpSocketClient(
                defaultExecutor,
                bootstrapBuilder,
                socketClientBuilder,
                this, sessionHandler,
                clientMessageDecode,
                clientMessageEncode
        );
        return tcpSocketClient;
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void postOnly() {
        if (tcpSocketClient != null) {
            rpcService.request(
                    tcpSocketClient.idleSession(),
                    "/game/online",
                    new JSONObject().fluentPut("sid", 1).fluentPut("onlineSize", RandomUtils.random(30000))
            );
        }
    }

}
