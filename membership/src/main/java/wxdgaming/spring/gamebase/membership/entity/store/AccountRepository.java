package wxdgaming.spring.gamebase.membership.entity.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wxdgaming.spring.boot.data.batis.BaseJpaRepository;
import wxdgaming.spring.gamebase.membership.entity.bean.Account;

/**
 * 账号仓储
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-14 13:53
 **/
@Repository
public interface AccountRepository extends BaseJpaRepository<Account, Long> {

    @Query("select count(a) > 0 from Account as a where a.name = ?1")
    boolean existsByName(String accountName);

    @Query("select a from Account as a where a.name = ?1")
    Account findByName(String accountName);

}
