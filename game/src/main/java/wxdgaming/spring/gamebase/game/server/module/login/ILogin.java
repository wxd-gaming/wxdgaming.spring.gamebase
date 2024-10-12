package wxdgaming.spring.gamebase.game.server.module.login;

import wxdgaming.spring.gamebase.game.server.bean.entity.user.Player;

/**
 * 玩家登录
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-11 09:46
 **/
public interface ILogin {

    /** 玩家登录 */
    void login(Player player);

}
