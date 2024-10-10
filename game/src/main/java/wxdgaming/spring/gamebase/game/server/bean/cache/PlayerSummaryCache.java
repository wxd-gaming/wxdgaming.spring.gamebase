package wxdgaming.spring.gamebase.game.server.bean.cache;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.gamebase.game.server.bean.EntityCache;
import wxdgaming.spring.gamebase.game.server.bean.entity.user.PlayerSummary;
import wxdgaming.spring.gamebase.game.server.bean.repository.PlayerSummaryJpaRepository;

/**
 * 角色快照缓存
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-16 23:56
 **/
@Getter
@Service
public class PlayerSummaryCache extends EntityCache<Long, PlayerSummary, PlayerSummaryJpaRepository> {

    @Getter static PlayerSummaryCache ins = null;

    @Autowired
    public PlayerSummaryCache(PlayerSummaryJpaRepository playerSummaryJpaRepository) {
        super(playerSummaryJpaRepository);
        ins = this;
    }

}
