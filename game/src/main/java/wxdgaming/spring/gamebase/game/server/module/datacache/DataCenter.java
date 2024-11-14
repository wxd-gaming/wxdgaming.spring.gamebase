package wxdgaming.spring.gamebase.game.server.module.datacache;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.threading.ExecutorBuilder;
import wxdgaming.spring.boot.core.threading.LogicExecutor;
import wxdgaming.spring.boot.core.threading.QueueEvent;
import wxdgaming.spring.boot.data.excel.store.DataRepository;
import wxdgaming.spring.gamebase.entity.Const;
import wxdgaming.spring.gamebase.game.server.bean.MapInfo;
import wxdgaming.spring.gamebase.game.server.bean.cache.PlayerMailCache;
import wxdgaming.spring.gamebase.game.server.bean.cache.ServerMailCache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据中心
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-16 20:26
 **/
@Slf4j
@Getter
@Service
public class DataCenter implements InitPrint {

    @Autowired PlayerMailCache roleMailCache;
    @Autowired ServerMailCache serverMailCache;
    @Autowired ExecutorBuilder executorBuilder;
    @Autowired LogicExecutor logicExecutor;
    @Autowired DataRepository dataRepository;

    int logicCoreSize;
    final ConcurrentHashMap<String, QueueEvent> queueEvents = new ConcurrentHashMap<>();
    final ConcurrentHashMap<Long, MapInfo> mapInfoHashMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void initData() {

        logicCoreSize = executorBuilder.getLogicCoreSize();
        for (int i = 0; i < logicCoreSize; i++) {
            String queueName = Const.MapQueue + i;
            queueEvents.put(queueName, new QueueEvent(queueName, logicExecutor));
        }
    }

    public void pushEvent(String queueName, Runnable runnable) {
        queueEvents.get(queueName).execute(runnable);
    }

}
