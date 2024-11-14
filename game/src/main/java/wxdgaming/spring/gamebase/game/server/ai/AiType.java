package wxdgaming.spring.gamebase.game.server.ai;

/**
 * ai类型
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-24 00:10
 **/
public enum AiType {
    /** 空闲 */
    IDLE,
    /** 寻找目标 */
    FIND_TARGET,
    /** 寻找路径点 */
    FIND_POINT,
    /** 移动 */
    MOVE,
    /** 战斗 */
    FIGHT,
    /** 死亡 */
    DIE,
    /** 复活 */
    REVIVE,
    ;
}
