package wxdgaming.spring.gamebase.game.server.module.datacache;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.gamebase.game.server.bean.cache.PlayerMailCache;
import wxdgaming.spring.gamebase.game.server.bean.cache.ServerMailCache;

/**
 * 数据中心
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-16 20:26
 **/
@Slf4j
@Getter
@Service
public class DataCenter implements InitPrint {

    @Autowired PlayerMailCache roleMailCache;
    @Autowired ServerMailCache serverMailCache;

    @PostConstruct
    public void s() {

    }

}
