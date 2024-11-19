package wxdgaming.spring.gamebase.game.script.module.map.tick;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wxdgaming.spring.gamebase.game.server.bean.IMapTickScript;
import wxdgaming.spring.gamebase.game.server.bean.MapInfo;

/**
 * 地图定时器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-11-18 10:55
 **/
@Slf4j
@Service
public class MapTickScript implements IMapTickScript {

    @Override public void tick(MapInfo mapInfo, long now) {
        // log.info("tick {}", mapInfo);

    }

}
