package wxdgaming.spring.gamebase.background.entity.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wxdgaming.spring.boot.data.batis.BaseJpaRepository;
import wxdgaming.spring.gamebase.background.entity.bean.Account;
import wxdgaming.spring.gamebase.background.entity.bean.ServerInfo;

import java.util.List;

/**
 * 服务器仓储
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-14 13:53
 **/
@Repository
public interface ServerInfoRepository extends BaseJpaRepository<ServerInfo, Integer> {

    @Query("select a from ServerInfo as a where a.gameId = ?1")
    List<ServerInfo> findAllByGameId(int gameId);

}
