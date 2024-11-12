package wxdgaming.spring.gamebase.game.server.client;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Configuration
@ConfigurationProperties("socket.client.login")
public class LoginClientBuild extends SocketClientBuilder.Config {

    @Autowired RpcService rpcService;
    TcpSocketClient loginSocket = null;

    @Bean(name = "loginClient")
    @ConditionalOnProperty(prefix = "socket.client.login", name = "port")
    public TcpSocketClient loginClient(DefaultExecutor defaultExecutor, BootstrapBuilder bootstrapBuilder,
                                       SocketClientBuilder socketClientBuilder,
                                       SessionHandler sessionHandler,
                                       ClientMessageDecode clientMessageDecode,
                                       ClientMessageEncode clientMessageEncode) {

        loginSocket = new TcpSocketClient(
                defaultExecutor,
                bootstrapBuilder,
                socketClientBuilder,
                this, sessionHandler,
                clientMessageDecode,
                clientMessageEncode
        );
        return loginSocket;
    }


}
