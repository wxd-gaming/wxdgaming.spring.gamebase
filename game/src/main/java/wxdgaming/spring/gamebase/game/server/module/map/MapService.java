package wxdgaming.spring.gamebase.game.server.module.map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.ann.Start;
import wxdgaming.spring.boot.data.excel.store.DataRepository;
import wxdgaming.spring.gamebase.entity.cfg.QMapTable;
import wxdgaming.spring.gamebase.entity.cfg.bean.QMap;
import wxdgaming.spring.gamebase.game.server.bean.MapInfo;
import wxdgaming.spring.gamebase.game.server.module.datacache.DataCenter;

import java.util.List;

/**
 * 地图服务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-16 20:26
 **/
@Slf4j
@Service
public class MapService implements InitPrint {

    final DataRepository dataRepository;
    final DataCenter dataCenter;


    public MapService(DataRepository dataRepository, DataCenter dataCenter) {
        this.dataRepository = dataRepository;
        this.dataCenter = dataCenter;
    }

    @Start
    public void start(SpringUtil springUtil) {
        QMapTable mapTable = this.dataRepository.dataTable(QMapTable.class);
        List<QMap> dataList = mapTable.getDataList();
        for (QMap cfg : dataList) {
            MapInfo mapInfo = new MapInfo();
            mapInfo.setUid(System.nanoTime());
            mapInfo.setCfgId(cfg.getId());
            log.info("初始化地图：{}", mapInfo);
            dataCenter.getMapInfoHashMap().put(mapInfo.getUid(), mapInfo);
            mapInfo.initTick();
        }
    }

}
