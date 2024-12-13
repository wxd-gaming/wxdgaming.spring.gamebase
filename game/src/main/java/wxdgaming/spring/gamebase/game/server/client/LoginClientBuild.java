package wxdgaming.spring.gamebase.game.server.client;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import wxdgaming.spring.boot.net.client.SocketClient;
import wxdgaming.spring.boot.rpc.RpcService;

/**
 * login server client
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-07 11:18
 **/
@Getter
@Configuration
@ConditionalOnProperty(prefix = "socket.client.config", name = "port")
public class LoginClientBuild {

    RpcService rpcService;
    SocketClient loginSocket = null;

    @PostConstruct
    public void init(RpcService rpcService,
                     @Qualifier("socketClient") final SocketClient socketClient) {
        this.rpcService = rpcService;
        this.loginSocket = socketClient;
    }

}
