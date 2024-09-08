package wxdgaming.spring.gamebase.login.module.login;

import com.alibaba.fastjson.JSONObject;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.core.timer.MyClock;
import wxdgaming.spring.boot.core.util.Md5Util;
import wxdgaming.spring.gamebase.login.data.entity.User;

/**
 * 登录接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-07 21:38
 **/
public interface ILogin {

    LoginChannel channel();

    /**
     * 登录
     *
     * @param account 账户
     * @param token   密钥
     * @param params  其他参数
     * @return 返回
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-09-08 09:07
     */
    RunResult login(String account, String token, JSONObject params);

    /** 登录之后处理 */
    default RunResult loginAfter(User user) {
        long time = MyClock.millis();
        String ret_token = Md5Util.md5DigestEncode0(
                "",
                user.getOpenId(),
                user.getAccount(),
                String.valueOf(time),
                channel().name(),
                LoginService.PRIVATE_TOKEN
        );

        return RunResult.ok()
                .fluentPut("openId", user.getOpenId())
                .fluentPut("time", time)
                .fluentPut("token", ret_token);
    }

}
