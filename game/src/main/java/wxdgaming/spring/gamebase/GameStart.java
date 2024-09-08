package wxdgaming.spring.gamebase;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import reactor.core.publisher.Mono;
import wxdgaming.spring.boot.core.CoreScan;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.ann.Start;
import wxdgaming.spring.boot.data.batis.DataBatisScan;
import wxdgaming.spring.boot.data.excel.DataExcelScan;
import wxdgaming.spring.boot.data.redis.DataRedisScan;
import wxdgaming.spring.boot.net.NetScan;
import wxdgaming.spring.boot.net.client.TcpSocketClient;
import wxdgaming.spring.boot.rpc.RpcScan;
import wxdgaming.spring.boot.rpc.RpcService;
import wxdgaming.spring.boot.web.WebScan;

import java.util.Arrays;

/**
 * 启动器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-08 19:54
 **/
@Slf4j
@EnableScheduling
@SpringBootApplication(
        scanBasePackageClasses = {
                GameStart.class,
                CoreScan.class,
                DataBatisScan.class,
                DataRedisScan.class,
                DataExcelScan.class,
                NetScan.class,
                RpcScan.class,
                WebScan.class,
        },
        exclude = {
                DataSourceAutoConfiguration.class,
                MongoAutoConfiguration.class
        }
)
public class GameStart {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(GameStart.class, args);
        SpringUtil.getIns().executor(Start.class);
        RpcService rpcService = run.getBean(RpcService.class);
        TcpSocketClient loginClient = (TcpSocketClient) run.getBean("loginClient");

        Mono<String> request = rpcService.request(
                loginClient.idleSession(),
                "/sdk/login",
                new JSONObject().fluentPut("account", "test1")
                        .fluentPut("token", "1")
                        .fluentPut("channel", "local")
        );
        request.subscribe(string -> log.info("登录结果: {}", string));
    }

}