package wxdgaming.spring.gamebase.game.server.bean.cache;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.gamebase.game.server.bean.EntityCache;
import wxdgaming.spring.gamebase.game.server.bean.entity.mail.PlayerMail;
import wxdgaming.spring.gamebase.game.server.bean.repository.PlayerMailJpaRepository;

/**
 * 角色邮件
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-16 23:56
 **/
@Getter
@Service
public class PlayerMailCache extends EntityCache<Long, PlayerMail, PlayerMailJpaRepository> {

    @Getter static PlayerMailCache ins = null;

    @Autowired
    public PlayerMailCache(PlayerMailJpaRepository playerMailJpaRepository) {
        super(playerMailJpaRepository);
        ins = this;
    }

}
