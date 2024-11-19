package wxdgaming.spring.gamebase.login.module.login;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.JwtBuilder;
import wxdgaming.spring.boot.core.LogbackUtil;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.core.timer.MyClock;
import wxdgaming.spring.boot.core.util.JwtUtils;
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
        JwtBuilder jwtBuilder = JwtUtils.createJwt(SpringUtil.getIns().getBean(LoginService.class).getPRIVATE_TOKEN());
        String compact = jwtBuilder
                .claim("userId", user.getOpenId())
                .claim("account", user.getAccount())
                .claim("time", time)
                .claim("channel", user.getChannel())
                .claim("channelName", channel().name())
                .compact();
        user.setLoginTime(MyClock.millis());
        user.setLoginCount(user.getLoginCount() + 1);
        LogbackUtil.logger().info("登录成功: {}", user);
        return RunResult.ok().data(compact);
    }

}
