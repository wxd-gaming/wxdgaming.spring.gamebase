package wxdgaming.spring.gamebase.login.module.login;

import com.alibaba.fastjson.JSONObject;
import wxdgaming.spring.boot.core.LogbackUtil;
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

    LoginService getLoginService();

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
        /*生成前端和游戏服或者网关交换时需要的密钥*/
        String ret_token = Md5Util.md5DigestEncode(
                user.getOpenId(),
                user.getAccount(),
                String.valueOf(time),
                channel().name(),
                LoginService.PRIVATE_TOKEN
        );
        user.setLoginTime(MyClock.millis());
        user.setLoginCount(user.getLoginCount() + 1);
        LogbackUtil.logger().info("登录成功: {}", user);
        return RunResult.ok()
                .fluentPut("openId", user.getOpenId())
                .fluentPut("channel", user.getChannel())
                .fluentPut("time", time)
                .fluentPut("token", ret_token);
    }

}
