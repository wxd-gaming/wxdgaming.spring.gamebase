package wxdgaming.spring.game.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import wxdgaming.spring.boot.core.CoreScan;
import wxdgaming.spring.boot.core.ScriptChildContext;
import wxdgaming.spring.boot.core.SpringReflect;
import wxdgaming.spring.boot.core.ann.LogicStart;
import wxdgaming.spring.boot.core.ann.ReLoad;
import wxdgaming.spring.boot.data.DataScan;
import wxdgaming.spring.boot.data.batis.DataJdbcScan;
import wxdgaming.spring.boot.data.redis.DataRedisScan;
import wxdgaming.spring.boot.loader.JavaCoderCompile;
import wxdgaming.spring.boot.net.NetScan;
import wxdgaming.spring.boot.rpc.RpcScan;
import wxdgaming.spring.boot.web.WebScan;
import wxdgaming.spring.boot.webclient.WebClientScan;

/**
 * 游戏启动
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-09 20:33
 **/
@Slf4j
@SpringBootApplication(
        scanBasePackageClasses = {
                CoreScan.class,
                DataScan.class,
                DataJdbcScan.class,
                DataRedisScan.class,
                NetScan.class,
                RpcScan.class,
                WebScan.class,
                WebClientScan.class,
                GameServerStart.class,
        },
        exclude = {
                DataSourceAutoConfiguration.class,
                MongoAutoConfiguration.class
        }
)
public class GameServerStart {

    static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) throws Exception {
        applicationContext = SpringApplication.run(GameServerStart.class, args);
        applicationContext.getBean(SpringReflect.class).content().executorAppStartMethod();
        loadScript();
    }

    public static void loadScript() throws Exception {
        ScriptChildContext scriptChildContext = applicationContext.getBean(ScriptChildContext.class);
        JavaCoderCompile javaCoderCompile = new JavaCoderCompile();
        ClassLoader parentClassLoader = GameServerStart.class.getClassLoader();
        javaCoderCompile.parentClassLoader(parentClassLoader);
        javaCoderCompile.compilerJava("game-server-script/src/main/java");
        javaCoderCompile.outPutFile("target/script", true);

        ConfigurableApplicationContext configurableApplicationContext = scriptChildContext.newChild4Classes(applicationContext, parentClassLoader, GameScriptScan.class, "target/script");
        SpringReflect bean = configurableApplicationContext.getBean(SpringReflect.class);
        bean.content().executorMethod(LogicStart.class);
        bean.content().executorMethod(ReLoad.class);
    }

    @ComponentScan(basePackages = {"wxdgaming.spring.game.script"})
    public static class GameScriptScan {}

}
