package wxdgaming.spring.gamebase.background.entity.store;

import org.springframework.stereotype.Repository;
import wxdgaming.spring.boot.data.batis.BaseRepository;
import wxdgaming.spring.gamebase.background.entity.bean.GameInfo;

/**
 * 游戏仓储
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-14 13:53
 **/
@Repository
public interface GameInfoRepository extends BaseRepository<GameInfo, Integer> {

}
