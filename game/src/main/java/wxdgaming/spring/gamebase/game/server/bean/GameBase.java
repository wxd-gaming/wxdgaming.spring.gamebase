package wxdgaming.spring.gamebase.game.server.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.batis.EntityBase;

/**
 * 基类
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-17 11:18
 **/
@Getter
@Setter
@Accessors(chain = true)
public class GameBase extends EntityBase<Long> {

    /** 配置ID */
    private int cfgId;

    @Override public GameBase setUid(Long uid) {
        super.setUid(uid);
        return this;
    }
}
