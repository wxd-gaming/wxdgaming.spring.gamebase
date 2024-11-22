package wxdgaming.spring.gamebase.game.server.bean.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wxdgaming.spring.boot.data.batis.BaseJpaRepository;
import wxdgaming.spring.gamebase.game.server.bean.entity.global.GlobalDataBean;

import java.util.List;

/**
 * 全局数据仓储
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-11-21 21:23
 */
@Repository
public interface GlobalDataJpaRepository extends BaseJpaRepository<GlobalDataBean, Long> {

    /** 查询所有尚未合并过的数据 */
    @Query("select g from GlobalDataBean as g where g.mergeTime=0")
    List<GlobalDataBean> findAllNotMerge();

}
