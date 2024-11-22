package wxdgaming.spring.gamebase.game.script.module.map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.data.excel.store.DataRepository;
import wxdgaming.spring.gamebase.game.script.module.event.ILevelUp;
import wxdgaming.spring.gamebase.game.script.module.event.ILogin;
import wxdgaming.spring.gamebase.game.server.bean.entity.user.Player;
import wxdgaming.spring.gamebase.game.server.module.datacache.DataCenter;

/**
 * map serer script
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-16 20:28
 **/
@Slf4j
@Service
public class MapServiceScript implements InitPrint, ILogin, ILevelUp {

    final DataRepository dataRepository;
    final DataCenter dataCenter;

    public MapServiceScript(DataRepository dataRepository, DataCenter dataCenter) {
        this.dataRepository = dataRepository;
        this.dataCenter = dataCenter;
    }

    @Override public void onLogin(Player player) {

    }

    @Override public void onLevelUp(Player player, int oldLevel) {

    }


}
