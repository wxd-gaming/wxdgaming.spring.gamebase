package wxdgaming.spring.gamebase.game.script.module.event;

import wxdgaming.spring.gamebase.game.server.bean.entity.user.Player;

/**
 * 登录事件接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-11-20 13:59
 **/
public interface ILogin {

    void onLogin(Player player);

}
