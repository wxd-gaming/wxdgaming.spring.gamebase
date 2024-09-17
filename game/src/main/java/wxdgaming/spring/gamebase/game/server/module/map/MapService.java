package wxdgaming.spring.gamebase.game.server.module.map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.ann.Start;
import wxdgaming.spring.boot.data.excel.ExcelRepository;

/**
 * 地图服务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-16 20:26
 **/
@Slf4j
@Service
public class MapService implements InitPrint {

    @Autowired ExcelRepository excelRepository;

    @Start
    public void start(SpringUtil springUtil) {
        String s = excelRepository.tableData("mapcfg").flatMap(t -> t.row(10001)).map(r -> r.getString("name")).orElse("null");
        log.info(s);
    }

}
