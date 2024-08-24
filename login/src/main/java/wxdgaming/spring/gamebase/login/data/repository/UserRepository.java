package wxdgaming.spring.gamebase.login.data.repository;

import org.springframework.stereotype.Repository;
import wxdgaming.spring.boot.data.batis.BaseJpaRepository;
import wxdgaming.spring.gamebase.login.data.entity.User;

/**
 * 用户
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-24 16:33
 **/
@Repository
public interface UserRepository extends BaseJpaRepository<User, String> {

}
