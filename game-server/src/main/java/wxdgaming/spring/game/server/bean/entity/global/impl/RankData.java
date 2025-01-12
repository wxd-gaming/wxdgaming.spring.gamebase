package wxdgaming.spring.game.server.bean.entity.global.impl;

import wxdgaming.spring.game.server.bean.entity.global.GlobalDataBase;
import wxdgaming.spring.game.server.bean.entity.global.GlobalType;

/**
 * 排行榜
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-11 18:28
 **/
public class RankData extends GlobalDataBase {

    @Override public GlobalType getType() {
        return GlobalType.Rank;
    }

}
