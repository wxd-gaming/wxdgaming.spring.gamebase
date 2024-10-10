package wxdgaming.spring.gamebase.game.server.bean.cache;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.gamebase.game.server.bean.EntityCache;
import wxdgaming.spring.gamebase.game.server.bean.entity.user.Player;
import wxdgaming.spring.gamebase.game.server.bean.repository.PlayerJpaRepository;

/**
 * 角色邮件
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-16 23:56
 **/
@Getter
@Service
public class PlayerCache extends EntityCache<Long, Player, PlayerJpaRepository> {

    @Getter static PlayerCache ins = null;

    @Autowired
    public PlayerCache(PlayerJpaRepository playerJpaRepository) {
        super(playerJpaRepository);
        ins = this;
    }

}
