package wxdgaming.spring.gamebase.login.module.login.sdk;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.gamebase.login.data.entity.User;
import wxdgaming.spring.gamebase.login.module.login.ILogin;
import wxdgaming.spring.gamebase.login.module.login.LoginChannel;
import wxdgaming.spring.gamebase.login.module.login.LoginService;

/**
 * 本地sdk
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-07 21:37
 **/
@Slf4j
@Getter
@Service
public class LocalSdk implements ILogin {

    final LoginService loginService;

    public LocalSdk(LoginService loginService) {this.loginService = loginService;}

    @Override public LoginChannel channel() {
        return LoginChannel.Local;
    }

    @Override public RunResult login(String account, String token, JSONObject params) {
        /*第一步查询user数据*/
        User user = loginService.find(channel(), account, token, params);

        if (!user.getToken().equalsIgnoreCase(token)) {
            return RunResult.error("token error");
        }

        return loginAfter(user);
    }

}
