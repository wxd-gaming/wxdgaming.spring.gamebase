package wxdgaming.spring.gamebase.game.server.module.datacache;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.collection.concurrent.ConcurrentTable;
import wxdgaming.spring.boot.core.json.FastJsonUtil;
import wxdgaming.spring.boot.core.threading.ExecutorBuilder;
import wxdgaming.spring.boot.core.threading.LogicExecutor;
import wxdgaming.spring.boot.core.threading.QueueEvent;
import wxdgaming.spring.boot.data.excel.store.DataRepository;
import wxdgaming.spring.gamebase.entity.Const;
import wxdgaming.spring.gamebase.game.server.bean.MapInfo;
import wxdgaming.spring.gamebase.game.server.bean.cache.PlayerMailCache;
import wxdgaming.spring.gamebase.game.server.bean.cache.ServerMailCache;
import wxdgaming.spring.gamebase.game.server.bean.entity.global.GlobalDataBean;
import wxdgaming.spring.gamebase.game.server.bean.repository.GlobalDataJpaRepository;

import java.util.List;
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

    final PlayerMailCache roleMailCache;
    final ServerMailCache serverMailCache;
    final GlobalDataJpaRepository globalDataJpaRepository;
    final ExecutorBuilder executorBuilder;
    final LogicExecutor logicExecutor;
    final DataRepository dataRepository;

    int logicCoreSize;
    final ConcurrentHashMap<String, QueueEvent> queueEvents = new ConcurrentHashMap<>();
    final ConcurrentHashMap<Long, MapInfo> mapInfoHashMap = new ConcurrentHashMap<>();
    final ConcurrentTable<Integer, Integer, GlobalDataBean> globalDataBeanMap = new ConcurrentTable<>();

    public DataCenter(PlayerMailCache roleMailCache,
                      ServerMailCache serverMailCache,
                      ExecutorBuilder executorBuilder,
                      LogicExecutor logicExecutor,
                      DataRepository dataRepository, GlobalDataJpaRepository globalDataJpaRepository) {
        this.roleMailCache = roleMailCache;
        this.serverMailCache = serverMailCache;
        this.executorBuilder = executorBuilder;
        this.logicExecutor = logicExecutor;
        this.dataRepository = dataRepository;
        this.globalDataJpaRepository = globalDataJpaRepository;
    }

    @PostConstruct
    public void initData() {

        logicCoreSize = executorBuilder.getLogicCoreSize();
        for (int i = 0; i < logicCoreSize; i++) {
            String queueName = Const.MapQueue + i;
            queueEvents.put(queueName, new QueueEvent(queueName, logicExecutor));
        }

        List<GlobalDataBean> all = this.globalDataJpaRepository.findAllNotMerge();
        for (GlobalDataBean globalDataBean : all) {
            GlobalDataBean parse = FastJsonUtil.parse(globalDataBean.getData(), GlobalDataBean.class);
            globalDataBeanMap.put(globalDataBean.getSid(), globalDataBean.getTypeId(), parse);
        }

    }



    public QueueEvent getQueueEvent(String queueName) {
        return queueEvents.get(queueName);
    }

    public void pushEvent(String queueName, Runnable runnable) {
        queueEvents.get(queueName).execute(runnable);
    }

}
