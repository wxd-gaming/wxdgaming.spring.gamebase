package wxdgaming.spring.gamebase.login.module.login.sdk;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.gamebase.login.module.login.ILogin;
import wxdgaming.spring.gamebase.login.module.login.LoginChannel;

/**
 * quick sdk
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-07 21:36
 **/
@Slf4j
@Service
public class QuickSdk implements ILogin {

    @Override public LoginChannel channel() {
        return LoginChannel.Quick;
    }

    @Override public RunResult login(String account, String token, JSONObject params) {

        return null;
    }

}
