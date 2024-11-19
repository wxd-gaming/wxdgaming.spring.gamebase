package wxdgaming.spring.gamebase.game.server.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.collection.concurrent.ConcurrentTable;
import wxdgaming.spring.boot.core.script.IScriptByKey;
import wxdgaming.spring.boot.core.script.ScriptService;
import wxdgaming.spring.boot.core.threading.Event;
import wxdgaming.spring.boot.core.threading.QueueEvent;
import wxdgaming.spring.boot.core.timer.MyClock;
import wxdgaming.spring.boot.data.excel.store.DataRepository;
import wxdgaming.spring.gamebase.entity.cfg.QMapTable;
import wxdgaming.spring.gamebase.entity.cfg.bean.QMap;
import wxdgaming.spring.gamebase.game.server.bean.entity.user.Player;
import wxdgaming.spring.gamebase.game.server.module.datacache.DataCenter;

import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Getter
@Setter
@Accessors(chain = true)
public class MapInfo extends GameBase implements AutoCloseable {

    private final ConcurrentTable<MapObject.ObjectType, Long, MapObject> mapObjects = new ConcurrentTable<>();

    ScheduledFuture<?> scheduleWithFixedDelay;

    public void initTick() {
        if (scheduleWithFixedDelay != null) throw new RuntimeException("");
        Runnable runnable = new Event() {
            int lastSecond = -1;
            int lastMinute = -1;
            int lastHour = -1;
            int lastDay = -1;

            @Override public void onEvent() throws Throwable {
                long millis = MyClock.millis();
                int second = MyClock.getSecond();
                int minute = MyClock.getMinute();
                int hour = MyClock.getHour();
                int day = MyClock.days();
                boolean isSecond = false;
                if (second != lastSecond) {
                    isSecond = lastSecond > -1;
                    lastSecond = second;
                }
                boolean isMinute = false;
                if (minute != lastMinute) {
                    isMinute = lastMinute > -1;
                    lastMinute = minute;
                }
                boolean isHour = false;
                if (hour != lastHour) {
                    isHour = lastHour > -1;
                    lastHour = hour;
                }
                boolean isDay = false;
                if (day != lastDay) {
                    isDay = lastDay > -1;
                    lastDay = day;
                }
                // 通用的心跳事件
                IMapTickScript mapTickScript = SpringUtil.getIns().getBean(IMapTickScript.class);
                mapTickScript.tick(MapInfo.this, millis);
                if (isSecond) mapTickScript.tickSecond(MapInfo.this, millis);
                if (isMinute) mapTickScript.tickMinute(MapInfo.this, millis);
                if (isHour) mapTickScript.tickHour(MapInfo.this, millis);
                if (isDay) mapTickScript.tickDay(MapInfo.this, millis);
                // 特殊地图自己实现的心跳事件
                ScriptService scriptService = SpringUtil.getIns().getBean(ScriptService.class);
                IScriptByKey serializableIScriptByKey = scriptService.getKeyScriptMap().get(IMapTickBuyIdScript.class.getName(), MapInfo.this.getCfgId());
                if (serializableIScriptByKey instanceof IMapTickBuyIdScript mapTickBuyIdScript) {
                    mapTickBuyIdScript.tick(MapInfo.this, millis);
                    if (isSecond) mapTickBuyIdScript.tickSecond(MapInfo.this, millis);
                    if (isMinute) mapTickBuyIdScript.tickMinute(MapInfo.this, millis);
                    if (isHour) mapTickBuyIdScript.tickHour(MapInfo.this, millis);
                    if (isDay) mapTickBuyIdScript.tickDay(MapInfo.this, millis);
                }
            }
        };

        scheduleWithFixedDelay = getQueueEvent().scheduleWithFixedDelay(runnable, 33, 33, TimeUnit.MILLISECONDS);
    }


    public QMap qMap() {
        return SpringUtil.getIns().getBean(DataRepository.class).dataTable(QMapTable.class).get(getCfgId());
    }

    public QueueEvent getQueueEvent() {
        return SpringUtil.getIns().getBean(DataCenter.class).getQueueEvent(getQueueName());
    }


    public void addEvent(Runnable event) {
        getQueueEvent().execute(event);
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

    @Override public void close() {
        scheduleWithFixedDelay.cancel(false);
    }

    @Override public String toString() {
        return "MapInfo{" +
               "uid=" + getUid() +
               ", cfg=" + getCfgId() +
               ", name=" + qMap().getName() +
               '}';
    }

}
