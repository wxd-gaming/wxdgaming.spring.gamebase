package wxdgaming.spring.gamebase.game.server.bean;

import lombok.Getter;
import wxdgaming.spring.boot.core.cache.Cache;
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
                .loader(this::loadEntity)
                .heartListener(this::heartListener)
                .removalListener(this::removalListener)
                .build();
    }

    /** 加载数据 */
    protected E loadEntity(ID id) {
        return r.findById(id).orElse(null);
    }

    /** 对数据做心跳处理，比如保存数据等 */
    protected void heartListener(ID id, E e) {}

    /** 缓存过期回调操作，默认保存数据 */
    protected boolean removalListener(ID id, E e) {
        r.save(e);
        return true;
    }

    /** 如果查询 null 返回null */
    public E getIfPresent(ID id) {
        return cache.getIfPresent(id);
    }

    /** 如果查询 null 异常 */
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
