package wxdgaming.spring.gamebase.login.module.login.sdk;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.webclient.HttpClientService;
import wxdgaming.spring.gamebase.login.data.entity.User;
import wxdgaming.spring.gamebase.login.module.login.ILogin;
import wxdgaming.spring.gamebase.login.module.login.LoginChannel;
import wxdgaming.spring.gamebase.login.module.login.LoginService;

/**
 * quick sdk
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-07 21:36
 **/
@Slf4j
@Getter
@Service
public class QuickSdk implements ILogin {

    final LoginService loginService;
    final HttpClientService httpClientService;

    @Autowired
    public QuickSdk(LoginService loginService, HttpClientService httpClientService) {
        this.loginService = loginService;
        this.httpClientService = httpClientService;
    }

    @Override public LoginChannel channel() {
        return LoginChannel.Quick;
    }

    @Override public RunResult login(String account, String token, JSONObject params) {
        /*第1步*/
        String bodyString = httpClientService.doPostText("", "").bodyString();

        if (!"ok".equalsIgnoreCase(bodyString)) {
            return RunResult.error("token error");
        }

        /*第2步查询user数据*/
        User user = loginService.find(channel(), account, token, params);

        return loginAfter(loginService.getPRIVATE_TOKEN(), user);
    }

}
