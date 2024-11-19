package wxdgaming.spring.gamebase.game.script.module.map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.data.excel.store.DataRepository;
import wxdgaming.spring.gamebase.game.server.module.datacache.DataCenter;

/**
 * map serer script
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-16 20:28
 **/
@Slf4j
@Service
public class MapServiceScript implements InitPrint {

    final DataRepository dataRepository;
    final DataCenter dataCenter;

    public MapServiceScript(DataRepository dataRepository, DataCenter dataCenter) {
        this.dataRepository = dataRepository;
        this.dataCenter = dataCenter;
    }


}
