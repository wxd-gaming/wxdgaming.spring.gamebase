package wxdgaming.spring.game.server.module.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.data.batis.JdbcContext;
import wxdgaming.spring.game.server.module.data.DbCacheService;

/**
 * 用户服务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-11 18:32
 **/
@Service
public class UserService implements InitPrint {

    final DbCacheService dbCacheService;
    final JdbcContext jdbcContext;


    @Autowired
    public UserService(DbCacheService dbCacheService, JdbcContext jdbcContext) {
        this.dbCacheService = dbCacheService;
        this.jdbcContext = jdbcContext;
    }


}
