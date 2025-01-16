package wxdgaming.spring.game.server.bean.entity.bag;

import lombok.Getter;
import lombok.Setter;
import wxdgaming.spring.boot.core.lang.ObjectBase;

/**
 * 道具
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-15 11:31
 **/
@Getter
@Setter
public class Item extends ObjectBase {

    private long uid;
    private long createdTime;
    private int cfgId;
    private long count;
    private int expireTime;

}
