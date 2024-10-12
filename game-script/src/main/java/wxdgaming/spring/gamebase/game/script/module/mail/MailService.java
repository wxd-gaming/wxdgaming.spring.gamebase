package wxdgaming.spring.gamebase.game.script.module.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.gamebase.game.server.bean.cache.PlayerMailCache;
import wxdgaming.spring.gamebase.game.server.bean.entity.user.Player;
import wxdgaming.spring.gamebase.game.server.module.login.ILogin;

/**
 * 邮件服务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-17 10:17
 **/
@Service
public class MailService implements InitPrint, ILogin {

    @Autowired PlayerMailCache playerMailCache;

    @Override public void login(Player player) {

    }

}
