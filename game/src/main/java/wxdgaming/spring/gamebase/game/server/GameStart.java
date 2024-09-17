package wxdgaming.spring.gamebase.game.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import wxdgaming.spring.boot.core.CoreScan;
import wxdgaming.spring.boot.core.LogbackUtil;
import wxdgaming.spring.boot.core.SpringChildContext;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.ann.Start;
import wxdgaming.spring.boot.core.system.JvmUtil;
import wxdgaming.spring.boot.data.batis.DataBatisScan;
import wxdgaming.spring.boot.data.excel.DataExcelScan;
import wxdgaming.spring.boot.data.redis.DataRedisScan;
import wxdgaming.spring.boot.net.NetScan;
import wxdgaming.spring.boot.rpc.RpcScan;
import wxdgaming.spring.boot.web.WebScan;

import java.io.File;

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
                CoreScan.class,
                DataBatisScan.class,
                DataRedisScan.class,
                DataExcelScan.class,
                NetScan.class,
                RpcScan.class,
                WebScan.class,
                GameStart.class,
        },
        exclude = {
                DataSourceAutoConfiguration.class,
                MongoAutoConfiguration.class
        }
)
public class GameStart {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(GameStart.class, args);
        try {
            SpringUtil.getIns().executor(Start.class);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            JvmUtil.halt(1);
        }

        SpringChildContext childContext = run.getBean(SpringChildContext.class);

        File path = new File("game-script.jar");
        if (path.exists()) {
            childContext.newChild4Jar(GameStart.class.getClassLoader(), ScriptScan.class, path.getPath());
        } else {
            childContext.newChild4JavaCode(
                    GameStart.class.getClassLoader(),
                    ScriptScan.class,
                    "game-script/src/main/java",
                    "wxdgaming.chargame.server-scripts/src/main/resources"
            );
        }

        // RpcService rpcService = run.getBean(RpcService.class);
        // TcpSocketClient loginClient = (TcpSocketClient) run.getBean("loginClient");

        // Mono<String> request = rpcService.request(
        //         loginClient.idleSession(),
        //         "/sdk/login",
        //         new JSONObject().fluentPut("account", "test2")
        //                 .fluentPut("token", "test22")
        //                 .fluentPut("channel", "local")
        // );
        // request.subscribe(string -> log.info("登录结果: {}", string));
    }

    @EnableAsync
    @EnableScheduling
    @ComponentScan(basePackages = "wxdgaming.spring.gamebase.game.script")
    public static class ScriptScan {
    }

}