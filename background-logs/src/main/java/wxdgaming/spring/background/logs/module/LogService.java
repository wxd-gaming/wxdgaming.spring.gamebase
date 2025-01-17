package wxdgaming.spring.background.logs.module;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wxdgaming.spring.background.logs.log.Slog;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.data.batis.JdbcContext;
import wxdgaming.spring.boot.data.batis.JdbcHelper;

/**
 * 日志写入
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-17 16:10
 **/
@Slf4j
@Service
public class LogService implements InitPrint {

    final JdbcHelper jdbcHelper;
    final JdbcContext jdbcContext;

    public LogService(JdbcHelper jdbcHelper, JdbcContext jdbcContext) {
        this.jdbcHelper = jdbcHelper;
        this.jdbcContext = jdbcContext;
    }

    public void pushLog(Slog slog) {

    }

}
