package wxdgaming.spring.gamebase.login.module.login;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.ann.Start;
import wxdgaming.spring.boot.core.timer.MyClock;
import wxdgaming.spring.boot.core.util.Md5Util;
import wxdgaming.spring.gamebase.login.data.entity.User;
import wxdgaming.spring.gamebase.login.data.repository.UserRepository;

import java.util.HashMap;
import java.util.Optional;

/**
 * 登录服务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-08 09:04
 **/
@Slf4j
@Getter
@Service
public class LoginService implements InitPrint {

    public static final String PRIVATE_TOKEN = "ddddd";
    // final Cache<String, User> userCache;
    final UserRepository userRepository;
    final HashMap<LoginChannel, ILogin> iLoginHashMap = new HashMap<>();

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
        // userCache = Cache.<String, User>builder()
        //         .expireAfterAccess(TimeUnit.HOURS.toMillis(12))/*12小时过期*/
        //         .build();
    }

    @Start
    public void start(SpringUtil springUtil) {
        springUtil
                .getBeansOfType(ILogin.class)
                .forEach(ilogin -> {
                    iLoginHashMap.put(ilogin.channel(), ilogin);
                    log.debug("register login channel {} controller {}", ilogin.channel(), ilogin.getClass().getName());
                });

        for (LoginChannel channel : LoginChannel.values()) {
            if (!iLoginHashMap.containsKey(channel)) {
                log.warn("login channel {} 未实现", channel.name());
            }
        }
    }

    /**
     * 查找账户信息,如果不存在会直接创建
     *
     * @param userName 账户
     * @param token    密钥
     * @param other    其他参数
     * @return 返回
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-09-08 09:07
     */
    public User find(LoginChannel loginChannel, String userName, String token, JSONObject other) {
        final String openId = Md5Util.md5DigestEncode(loginChannel.name(), userName, PRIVATE_TOKEN);
        synchronized (openId.intern()) {
            // User user = userCache.get(openId);
            // if (user == null) {
            Optional<User> byId = userRepository.findById(openId);
            User user = byId.orElse(null);
            if (user == null) {
                user = new User()
                        .setCreateTime(MyClock.millis())
                        .setOpenId(openId)
                        .setChannel(loginChannel.name())
                        .setAccount(userName)
                        .setToken(token);
            }
            // }
            /*这里是记录最后一次登录other数据*/
            user.setOther(other);
            /*设置被触摸的时间 方便运营分析的*/
            user.setTouchTime(MyClock.millis());
            userRepository.save(user);
            return user;
        }
    }

}
