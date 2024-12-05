package wxdgaming.spring.gamebase.background.entity.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wxdgaming.spring.boot.data.batis.BaseRepository;
import wxdgaming.spring.gamebase.background.entity.bean.ServerInfo;

import java.util.List;
import java.util.Optional;

/**
 * 服务器仓储
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-14 13:53
 **/
@Repository
public interface ServerInfoRepository extends BaseRepository<ServerInfo, Integer> {

    @Query("select a from ServerInfo as a where a.gameId = ?1")
    List<ServerInfo> findAllByGameId(int gameId);

    /**
     * 查询服务器列表
     *
     * @param gameId   游戏id
     * @param platform 平台
     * @return
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-10-19 19:41
     */
    @Query("select a from ServerInfo as a where a.gameId = ?1 and a.platform=?2")
    List<ServerInfo> findAllByGP(int gameId, String platform);

    /**
     * 查询服务器列表
     *
     * @param gameId   游戏id
     * @param platform 平台
     * @param sid      服务器id
     * @return
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-10-19 19:41
     */
    @Query("select a from ServerInfo as a where a.gameId = ?1 and a.platform= ?2 and a.sid = ?3")
    Optional<ServerInfo> findOneGPS(int gameId, String platform, int sid);

}
