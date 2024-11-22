package wxdgaming.spring.gamebase.game.script.module.user;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.threading.ThreadContext;
import wxdgaming.spring.boot.net.SocketSession;
import wxdgaming.spring.boot.rpc.RPC;
import wxdgaming.spring.gamebase.game.script.module.event.ILevelUp;
import wxdgaming.spring.gamebase.game.script.module.event.ILogin;
import wxdgaming.spring.gamebase.game.server.bean.entity.user.Player;

/**
 * 用户模块
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-11-20 13:56
 */
@Slf4j
@Service
@RequestMapping("/user")
public class UserScript implements InitPrint {

    @RPC(value = "/login")
    public void login(SocketSession session, JSONObject putData) {
        Player player = ThreadContext.context(Player.class);
        /* TODO 嵌入检查，比如维护白名单，是否已经被封号等等 */
        SpringUtil.getIns()
                .reflectContext()
                .classWithSuper(ILogin.class)
                .forEach(loginScript -> loginScript.onLogin(player));
    }

    public void levelUp(Player player) {

        SpringUtil.getIns()
                .reflectContext()
                .classWithSuper(ILevelUp.class)
                .forEach(loginScript -> loginScript.onLevelUp(player, 1));

    }

}
