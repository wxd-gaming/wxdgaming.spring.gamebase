package wxdgaming.spring.gamebase.game.server.bean;

import wxdgaming.spring.boot.core.script.IScriptSingleton;

/**
 * 地图心跳
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-11-18 11:17
 **/
public interface IMapTickScript extends IScriptSingleton {

    /**
     * @param mapInfo 当前地图
     * @param now     当前时间戳
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-11-18 15:49
     */
    void tick(MapInfo mapInfo, long now);

    /**
     * 跨秒执行
     *
     * @param mapInfo 当前地图
     * @param now     当前时间戳
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-11-18 15:52
     */
    default void tickSecond(MapInfo mapInfo, long now) {}

    /**
     * 跨分钟执行
     *
     * @param mapInfo 当前地图
     * @param now     当前时间戳
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-11-18 15:51
     */
    default void tickMinute(MapInfo mapInfo, long now) {}

    /**
     * 跨小时
     *
     * @param mapInfo 当前地图
     * @param now     当前时间戳
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-11-18 15:51
     */
    default void tickHour(MapInfo mapInfo, long now) {}

    /**
     * 跨天执行事件
     *
     * @param mapInfo 当前地图
     * @param now     当前时间戳
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-11-18 15:51
     */
    default void tickDay(MapInfo mapInfo, long now) {}

}
