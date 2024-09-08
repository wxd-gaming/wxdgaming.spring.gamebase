package wxdgaming.spring.gamebase.login.module.rpc;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.net.SocketSession;
import wxdgaming.spring.boot.rpc.RPC;
import wxdgaming.spring.gamebase.login.module.login.ILogin;
import wxdgaming.spring.gamebase.login.module.login.LoginChannel;

/**
 * 提供给游戏服的使用的
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-07 16:16
 **/
@Slf4j
@Controller
@RequestMapping("/game")
public class GameController {

    @RPC
    public void online(SocketSession session,
                       @RequestParam("sid") int sid,
                       @RequestParam("onlineSize") int onlineSize
    ) {
        log.info("{}, 服务器：{}, 在线人数：{}", session, sid, onlineSize);
    }


}
