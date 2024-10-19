package wxdgaming.spring.gamebase.membership.entity.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wxdgaming.spring.boot.data.batis.BaseJpaRepository;
import wxdgaming.spring.gamebase.membership.entity.bean.Account;
import wxdgaming.spring.gamebase.membership.entity.bean.Stores;

/**
 * 门店
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-14 13:53
 **/
@Repository
public interface StoresRepository extends BaseJpaRepository<Stores, Integer> {

    @Query("select count(a) > 0 from Stores as a where a.name = ?1")
    boolean existsByName(String name);

    @Query("select a from Stores as a where a.name = ?1")
    Stores findByName(String name);

}