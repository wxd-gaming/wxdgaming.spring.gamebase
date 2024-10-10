package wxdgaming.spring.gamebase.game.server.bean.repository;

import org.springframework.stereotype.Repository;
import wxdgaming.spring.boot.data.batis.BaseJpaRepository;
import wxdgaming.spring.gamebase.game.server.bean.entity.user.PlayerSummary;

/**
 * 角色快照
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-10 19:52
 **/
@Repository
public interface PlayerSummaryJpaRepository extends BaseJpaRepository<PlayerSummary, Long> {
}
