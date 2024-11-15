package wxdgaming.spring.gamebase.game.script.module.map;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.data.excel.store.DataRepository;
import wxdgaming.spring.gamebase.entity.cfg.QMapTable;
import wxdgaming.spring.gamebase.entity.cfg.bean.QMap;
import wxdgaming.spring.gamebase.game.server.bean.MapInfo;
import wxdgaming.spring.gamebase.game.server.module.datacache.DataCenter;

import java.util.List;

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

    @PostConstruct
    public void initMap() {

    }

}
