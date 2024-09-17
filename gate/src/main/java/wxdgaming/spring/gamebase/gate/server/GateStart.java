package wxdgaming.spring.gamebase.gate.server;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import wxdgaming.spring.boot.broker.BrokerScan;
import wxdgaming.spring.boot.core.CoreScan;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.ann.Start;
import wxdgaming.spring.boot.net.NetScan;

/**
 * 网关启动器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-17 21:35
 */
@Slf4j
@SpringBootApplication(
        scanBasePackageClasses = {
                CoreScan.class,
                NetScan.class,
                BrokerScan.class,
                GateStart.class,
        },
        exclude = {
                DataSourceAutoConfiguration.class,
                MongoAutoConfiguration.class
        }
)
public class GateStart {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(GateStart.class, args);
        SpringUtil.getIns().executor(Start.class);
    }

}