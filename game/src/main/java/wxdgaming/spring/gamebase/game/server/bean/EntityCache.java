package wxdgaming.spring.gamebase.game.server.bean;

import lombok.Getter;
import wxdgaming.spring.boot.core.cache.Cache;
import wxdgaming.spring.boot.core.function.Consumer2;
import wxdgaming.spring.boot.core.function.Function2;
import wxdgaming.spring.boot.data.batis.BaseJpaRepository;
import wxdgaming.spring.boot.data.batis.EntityBase;

import java.util.concurrent.TimeUnit;

/**
 * 实体类缓存
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-16 23:44
 **/
@Getter
public class EntityCache<ID, E extends EntityBase<ID>, R extends BaseJpaRepository<E, ID>> {

    R r;
    Cache<ID, E> cache;

    public EntityCache(R r) {
        this.r = r;
        cache = Cache.<ID, E>builder()
                .cacheName(this.getClass().getName())
                .expireAfterAccess(12, TimeUnit.HOURS)
                .loader(uid -> r.findById(uid).orElse(null))
                .heartListener(new Consumer2<ID, E>() {
                    @Override public void accept(ID id, E e) {

                    }
                })
                .removalListener(new Function2<ID, E, Boolean>() {
                    @Override public Boolean apply(ID id, E e) {
                        return true;
                    }
                })
                .build();
    }

    public E get(ID id) {
        return cache.get(id);
    }

    public void put(E e) {
        ID uid = e.getUid();
        cache.put(uid, e);
        r.save(e);
    }


    public void clear() {
        cache.close();
    }


    public long cacheSize() {
        return cache.cacheSize();
    }

}
