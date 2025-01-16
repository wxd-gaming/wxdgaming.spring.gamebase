package wxdgaming.spring.game.server.bean.entity.user;

import lombok.Getter;
import lombok.Setter;
import wxdgaming.spring.game.server.bean.entity.bag.Item;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 背包容器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-15 11:18
 **/
@Getter
@Setter
public class BagPack {

    private final ConcurrentHashMap<Integer, Long> currency = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, Item> packs = new ConcurrentHashMap<>();

    public void add(Item item) {

    }

    public void remove(int cfgId, long count) {

    }

    public void remove(long uid, long count) {

    }

    public void remove(long uid) {

    }

}
