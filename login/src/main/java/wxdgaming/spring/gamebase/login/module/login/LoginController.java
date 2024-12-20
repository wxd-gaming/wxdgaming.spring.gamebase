package wxdgaming.spring.gamebase.login.module.login;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.rpc.RPC;

/**
 * login 处理
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-07 21:34
 **/
@Slf4j
@Controller
@RequestMapping("/sdk")
public class LoginController implements InitPrint {

    final LoginService loginService;

    public LoginController(LoginService loginService) {this.loginService = loginService;}

    @RPC(value = "/login")
    @ResponseBody
    @RequestMapping("/login")
    public RunResult login(@RequestParam(name = "channel") String channel,
                           @RequestParam(name = "account") String account,
                           @RequestParam(name = "token") String token,
                           @RequestParam(name = "other") String other
    ) {

        LoginChannel loginChannel = LoginChannel.of(channel);
        if (loginChannel == null) {
            return RunResult.error("channel error");
        }
        ILogin iLogin = loginService.getILoginHashMap().get(loginChannel);
        if (iLogin == null) {
            return RunResult.error("channel 未实现");
        }
        return iLogin.login(account, token, JSONObject.parseObject(other));

    }

}
