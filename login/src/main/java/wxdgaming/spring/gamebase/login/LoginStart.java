package wxdgaming.spring.gamebase.login;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import wxdgaming.spring.boot.core.CoreScan;
import wxdgaming.spring.boot.data.batis.DataJdbcScan;
import wxdgaming.spring.boot.net.NetScan;
import wxdgaming.spring.boot.rpc.RpcDispatcher;
import wxdgaming.spring.boot.rpc.RpcScan;
import wxdgaming.spring.boot.rpc.RpcService;
import wxdgaming.spring.boot.rpc.pojo.RpcMessage;
import wxdgaming.spring.boot.web.WebScan;
import wxdgaming.spring.boot.webclient.WebClientScan;

/**
 * 启动器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-08 19:54
 **/
@Slf4j
@EntityScan("wxdgaming.spring.gamebase.login")
@EnableJpaRepositories("wxdgaming.spring.gamebase.login")
@SpringBootApplication(
        scanBasePackageClasses = {
                LoginStart.class,
                CoreScan.class,
                DataJdbcScan.class,
                NetScan.class,
                RpcScan.class,
                WebScan.class,
                WebClientScan.class,
        },
        exclude = {
                DataSourceAutoConfiguration.class,
                MongoAutoConfiguration.class
        }
)
public class LoginStart {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(LoginStart.class, args);

        run.getBean(LoginSpringReflect.class).content().executorAppStartMethod();

        RpcService rpcService = run.getBean(RpcService.class);
        RpcDispatcher rpcDispatcher = run.getBean(RpcDispatcher.class);

        RpcMessage.ReqRPC rpcMessage = new RpcMessage.ReqRPC();
        rpcMessage
                .setRpcId(1)
                .setPath("rpcTest")
                .setParams(new JSONObject().fluentPut("type", 1).toString())
        ;

        // try {
        //     SocketSession session = run.getBean(TcpSocketClient.class).idleSession();
        //     session.writeAndFlush("string message");
        //     Mono<String> rpc = rpcService.request(session, "rpcTest", new JSONObject().fluentPut("type", 1).toString());
        //     rpc.subscribe(str -> log.debug("{}", str));
        //     // rpc.block();
        // } catch (Exception ignore) {}
        //
        // try {
        //     SocketSession session = run.getBean(WebSocketClient.class).idleSession();
        //     session.writeAndFlush("string message");
        //     Mono<String> rpc = rpcService.request(session, "rpcTest", new JSONObject().fluentPut("type", 1).toString());
        //     rpc.subscribe(str -> log.debug("{}", str));
        //     // rpc.block();
        // } catch (Exception ignore) {}

        // UserRepository userRepository = run.getBean(UserRepository.class);
        //
        // for (int i = 0; i < 100; i++) {
        //     long nanoTime = System.nanoTime();
        //     userRepository.saveAndFlush(new User().setOpenId(String.valueOf(System.nanoTime())).setAccount(RandomStringUtils.randomAlphanumeric(32)));
        //     log.info("插入 耗时：{} ms", (System.nanoTime() - nanoTime) / 10000 / 100f);
        // }
        // {
        //     List<User> users = new ArrayList<>();
        //     for (int i = 0; i < 100; i++) {
        //         users.add(new User().setOpenId(String.valueOf(System.nanoTime())).setAccount(RandomStringUtils.randomAlphanumeric(32)));
        //     }
        //     long nanoTime = System.nanoTime();
        //     userRepository.saveAllAndFlush(users);
        //     log.info("插入 耗时：{} ms", (System.nanoTime() - nanoTime) / 10000 / 100f);
        // }
    }

}
