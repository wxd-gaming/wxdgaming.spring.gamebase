package wxdgaming.spring.gamebase.background;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import wxdgaming.spring.boot.core.CoreScan;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.ann.Start;
import wxdgaming.spring.boot.data.batis.DataBatisScan;
import wxdgaming.spring.boot.data.excel.DataExcelScan;
import wxdgaming.spring.boot.data.redis.DataRedisScan;
import wxdgaming.spring.boot.net.NetScan;
import wxdgaming.spring.boot.rpc.RpcScan;
import wxdgaming.spring.boot.web.WebScan;

/**
 * 启动器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-14 13:48
 */
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
                BackgroundStart.class,
        },
        exclude = {
                DataSourceAutoConfiguration.class,
                MongoAutoConfiguration.class
        }
)
public class BackgroundStart {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BackgroundStart.class);
        SpringUtil.getIns().executor(Start.class);
    }

}