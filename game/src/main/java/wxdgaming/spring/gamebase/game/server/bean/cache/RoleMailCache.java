package wxdgaming.spring.gamebase.game.server.bean.cache;

import org.springframework.stereotype.Service;
import wxdgaming.spring.gamebase.game.server.bean.EntityCache;
import wxdgaming.spring.gamebase.game.server.bean.entity.mail.RoleMail;
import wxdgaming.spring.gamebase.game.server.bean.repository.RoleMailJpaRepository;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-16 23:56
 **/
@Service
public class RoleMailCache extends EntityCache<Long, RoleMail, RoleMailJpaRepository> {

    public RoleMailCache(RoleMailJpaRepository roleMailJpaRepository) {
        super(roleMailJpaRepository);
    }

}
