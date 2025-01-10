package wxdgaming.spring.game.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import wxdgaming.spring.boot.core.CoreScan;
import wxdgaming.spring.boot.data.DataScan;
import wxdgaming.spring.boot.data.batis.DataJdbcScan;
import wxdgaming.spring.boot.data.redis.DataRedisScan;
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
}
