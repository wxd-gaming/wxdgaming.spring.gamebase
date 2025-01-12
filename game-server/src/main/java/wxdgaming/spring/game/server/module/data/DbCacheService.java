package wxdgaming.spring.game.server.module.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.ReflectContext;
import wxdgaming.spring.boot.core.ann.LogicStart;
import wxdgaming.spring.boot.core.collection.concurrent.ConcurrentTable;
import wxdgaming.spring.boot.data.EntityUID;
import wxdgaming.spring.boot.data.batis.JdbcCache;
import wxdgaming.spring.boot.data.batis.JdbcContext;
import wxdgaming.spring.game.server.bean.entity.global.GlobalData;
import wxdgaming.spring.game.server.bean.entity.global.GlobalDataBase;
import wxdgaming.spring.game.server.bean.entity.global.GlobalType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库缓存服务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-11 18:19
 **/
@Component
public class DbCacheService implements InitPrint {


    private final ConcurrentHashMap<Class<? extends EntityUID<?>>, JdbcCache<?, ?>> cache = new ConcurrentHashMap<>();

    private final ConcurrentTable<Integer, GlobalType, GlobalData> globalDataMap = new ConcurrentTable<>();

    JdbcContext jdbcContext;

    @Autowired
    public DbCacheService(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    @LogicStart
    public void init(@Value("${game.config.sid}") int sid) {
        List<GlobalData> all = this.jdbcContext.findAll(GlobalData.class);
        for (GlobalData globalData : all) {
            globalDataMap.put(globalData.getSid(), globalData.getType(), globalData);
        }

        Map<GlobalType, GlobalData> row = globalDataMap.row(sid);

        ReflectContext.Builder builder = ReflectContext.Builder.of(GlobalDataBase.class.getPackageName());
        ReflectContext build = builder.build();
        build.withSuper(GlobalDataBase.class)
                .filter(v -> row.values().stream()
                        .noneMatch(d -> v.getCls().isAssignableFrom(d.getData().getClass())))
                .forEach(vClass -> {
                    GlobalDataBase globalBase = ReflectContext.newInstance(vClass.getCls());
                    GlobalData globalData = new GlobalData();
                    globalData.setSid(sid);
                    globalData.setType(globalBase.getType());
                    globalData.setData(globalBase);
                    globalData.setUid(globalData.getSid() * 10000L + globalData.getType().getCode());
                    globalDataMap.put(sid, globalBase.getType(), globalData);
                    jdbcContext.save(globalData);
                });

    }

    public <K, V extends EntityUID<K>> JdbcCache<K, V> get(Class<V> clazz) {
        return (JdbcCache<K, V>) cache.computeIfAbsent(clazz, k -> new JdbcCache<>(jdbcContext, clazz, 30));
    }

    public <K, V extends EntityUID<K>> V find(Class<V> clazz, K uid) {
        return get(clazz).get(uid);
    }

    public <K, V extends EntityUID<K>> void put(V v) {
        get(v.getClass()).put(v.getUid(), v);
    }

}
