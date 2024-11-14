package wxdgaming.spring.gamebase.game.server.ai;

import lombok.Getter;

/**
 * ai类型
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-24 00:10
 **/
@Getter
public enum AiSubType {
    /** 空闲 */
    IDLE(AiType.IDLE),
    /** 寻找目标 */
    FIND_TARGET(AiType.FIND_TARGET),
    /** 寻找路径点 */
    FIND_POINT(AiType.FIND_POINT),
    /** 移动 */
    MOVE(AiType.MOVE),
    /** 战斗 */
    FIGHT(AiType.FIGHT),
    /** 死亡 */
    DIE(AiType.DIE),
    /** 死亡后不允许复活 */
    DIENOTREVIVE(AiType.DIE),
    /** 复活 */
    REVIVE(AiType.REVIVE),
    ;
    private AiType aiType;

    AiSubType(AiType aiType) {
        this.aiType = aiType;
    }
}
