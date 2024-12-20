package wxdgaming.spring.gamebase.game.server.bean.repository;

import org.springframework.stereotype.Repository;
import wxdgaming.spring.boot.data.batis.BaseRepository;
import wxdgaming.spring.gamebase.game.server.bean.entity.mail.PlayerMail;

/**
 * 全服邮件仓储
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-16 23:34
 **/
@Repository
public interface PlayerMailJpaRepository extends BaseRepository<PlayerMail, Long> {

}
