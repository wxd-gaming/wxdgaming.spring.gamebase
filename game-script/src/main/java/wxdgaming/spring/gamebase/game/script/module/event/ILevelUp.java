package wxdgaming.spring.gamebase.game.script.module.event;

import wxdgaming.spring.gamebase.game.server.bean.entity.user.Player;

/**
 * 等级提升
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-11-20 14:46
 **/
public interface ILevelUp {

    void onLevelUp(Player player, int oldLevel);

}
