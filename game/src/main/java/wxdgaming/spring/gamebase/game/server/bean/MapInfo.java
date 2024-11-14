package wxdgaming.spring.gamebase.game.server.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.collection.concurrent.ConcurrentTable;
import wxdgaming.spring.boot.data.excel.store.DataRepository;
import wxdgaming.spring.gamebase.entity.Const;
import wxdgaming.spring.gamebase.entity.cfg.QMapTable;
import wxdgaming.spring.gamebase.entity.cfg.bean.QMap;
import wxdgaming.spring.gamebase.game.server.bean.entity.user.Player;
import wxdgaming.spring.gamebase.game.server.module.datacache.DataCenter;

import java.util.Map;

@Slf4j
@Getter
@Setter
@Accessors(chain = true)
public class MapInfo extends GameBase {

    private final ConcurrentTable<MapObject.ObjectType, Long, MapObject> mapObjects = new ConcurrentTable<>();

    private String queueName = null;

    public String getQueueName() {
        if (queueName == null) {
            if (getUid() > 0) {
                int i = getCfgId() % 10;
                queueName = Const.MapQueue + i;
            }
        }
        return queueName;
    }

    public QMap qMap() {
        return SpringUtil.getIns().getBean(DataRepository.class).dataTable(QMapTable.class).get(getCfgId());
    }

    public void addEvent(Runnable event) {
        SpringUtil.getIns().getBean(DataCenter.class).pushEvent(getQueueName(), event);
    }

    /**
     * 地图广播数据
     *
     * @param jsonData 格式是json数据
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-03-17 16:45
     */
    public void tellMap(String jsonData) {
        mapObjects.opt(MapObject.ObjectType.Player).ifPresent(v -> {
            v.values().forEach(p -> {
                Player player = (Player) p;
                // player.writeAndFlush(jsonData);
            });
        });
    }

    /** 是否有玩家 */
    public boolean havPlayer() {
        return mapObjects.opt(MapObject.ObjectType.Player).map(Map::size).orElse(0) > 0;
    }

    @Override public String toString() {
        return "MapInfo{" +
               "uid=" + getUid() +
               ", cfg=" + getCfgId() +
               ", name=" + qMap().getName() +
               '}';
    }

}
