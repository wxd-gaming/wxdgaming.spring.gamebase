package wxdgaming.spring.background.logs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import wxdgaming.spring.boot.core.CoreScan;
import wxdgaming.spring.boot.data.batis.DataJdbcScan;
import wxdgaming.spring.boot.web.WebScan;

/**
 * 日志服务启动器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-17 15:59
 **/
@Slf4j
@EnableScheduling
@EntityScan("wxdgaming.spring.background.logs.entity")
@SpringBootApplication(
        scanBasePackageClasses = {
                CoreScan.class,
                DataJdbcScan.class,
                WebScan.class,
                LogServiceStart.class,
        },
        exclude = {
                DataSourceAutoConfiguration.class,
                MongoAutoConfiguration.class
        }
)
public class LogServiceStart {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LogServiceStart.class, args);
        context.getBean(LogServiceReflect.class).content().executorAppStartMethod();
    }

}
