package wxdgaming.spring.gamebase.game.script.module.user;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wxdgaming.spring.boot.net.SocketSession;
import wxdgaming.spring.boot.rpc.RPC;

/**
 * 用户接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-11 20:17
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @RPC("/login")
    public void login(SocketSession session, JSONObject jsonObject) {

    }

}
