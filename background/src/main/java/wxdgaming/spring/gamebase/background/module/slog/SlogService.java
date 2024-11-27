package wxdgaming.spring.gamebase.background.module.slog;

import com.alibaba.fastjson.JSONObject;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.data.batis.DbHelper;
import wxdgaming.spring.boot.data.batis.DruidSourceConfig;
import wxdgaming.spring.gamebase.background.entity.slog.Slog;
import wxdgaming.spring.gamebase.background.entity.store.SlogRepository;

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
    final DbHelper dbHelper;
    final EntityManager nativeEntityManager;

    public SlogService(SlogRepository slogRepository, DbHelper dbHelper) {
        this.slogRepository = slogRepository;
        this.dbHelper = dbHelper;
        DruidSourceConfig copy = this.dbHelper.getDb().copy("base-test-2");
        nativeEntityManager = copy.entityManagerFactory(Slog.class.getPackageName());
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void postLog() {
        nativeEntityManager.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            Slog slog = new Slog();
            slog.setUid(System.nanoTime());
            slog.setGameId(1001);
            slog.setSid(1).setMainSid(1);

            slog.setRoleId(1).setRoleName("dd").setLv(1).setVipLv(0);
            slog.setLoginAccount("dd");
            slog.setClientIp("127.0.0.1");
            slog.setContent(new JSONObject().fluentPut("test", "test"));
            // slogRepository.save(slog);
            nativeEntityManager.persist(slog);
        }
        nativeEntityManager.getTransaction().commit();
    }

}
