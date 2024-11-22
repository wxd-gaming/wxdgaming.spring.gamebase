package wxdgaming.spring.gamebase.game.server.bean.entity.global.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.core.lang.rank.RankMap;
import wxdgaming.spring.boot.core.lang.rank.RankScore;
import wxdgaming.spring.gamebase.game.server.bean.RankType;
import wxdgaming.spring.gamebase.game.server.bean.entity.global.GlobalBase;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 排行榜数据
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-11-22 14:51
 **/
@Getter
@Setter
@Accessors(chain = true)
public class GlobalRank extends GlobalBase {

    private ConcurrentHashMap<RankType, RankMap<RankScore>> rankMap = new ConcurrentHashMap<>();

}
