package wxdgaming.spring.game.script.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.game.server.module.user.UserService;

/**
 * 用户接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-10 16:53
 **/
@Slf4j
@Service
public class UserSpi implements InitPrint {

    final UserService userService;

    public UserSpi(UserService userService) {
        this.userService = userService;
    }


}
