package wxdgaming.spring.gamebase.background.module.slog;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.format.HexId;
import wxdgaming.spring.boot.data.batis.DruidSourceConfig;
import wxdgaming.spring.boot.data.batis.JdbcContext;
import wxdgaming.spring.boot.data.batis.JdbcHelper;
import wxdgaming.spring.gamebase.background.entity.slog.Slog;
import wxdgaming.spring.gamebase.background.entity.store.SlogRepository;

import java.util.ArrayList;
import java.util.Map;

/**
 * 日志服务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-11-26 16:48
 **/
@Slf4j
@Service
public class SlogService {

    final SlogRepository slogRepository;
    final JdbcContext jdbcContext;
    HexId hexId = new HexId(1);

    public SlogService(SlogRepository slogRepository, JdbcHelper jdbcHelper) {
        this.slogRepository = slogRepository;
        DruidSourceConfig copy = jdbcHelper.getConfig().copy("base-test-2");
        copy.setShowSql(false);
        copy.setPackageNames(new String[]{Slog.class.getPackageName()});
        copy.createDatabase();
        DruidDataSource dataSource = copy.toDataSource();
        jdbcContext = new JdbcContext(dataSource, copy.entityManager(dataSource, Map.of()));
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void postLog() {
        // {
        //     nativeEntityManager.getTransaction().begin();
        //     nativeEntityManager.createNativeQuery("TRUNCATE slog;").executeUpdate();
        //     nativeEntityManager.getTransaction().commit();
        // }
        {
            ArrayList<Slog> logs = new ArrayList<>(10000);
            for (int i = 0; i < 10000; i++) {
                Slog slog = new Slog();
                slog.setUid(hexId.newId());
                slog.setGameId(1001);
                slog.setSid(1).setMainSid(1);

                slog.setRoleId(1).setRoleName("dd").setLv(1).setVipLv(0);
                slog.setLoginAccount("dd");
                slog.setClientIp("127.0.0.1");
                slog.setContent(new JSONObject().fluentPut("test", "test").fluentPut("box", 1));
                // slogRepository.save(slog);
                logs.add(slog);
            }
            long start = System.nanoTime();
            // jdbcContext.batchSave(logs);
            jdbcContext.batchInsert(logs);
            System.out.println(((System.nanoTime() - start) / 10000 / 100f) + " ms");
        }
    }

}
