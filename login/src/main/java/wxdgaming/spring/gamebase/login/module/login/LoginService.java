package wxdgaming.spring.gamebase.login.module.login;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.ann.Start;
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
    final UserRepository userRepository;
    private final HashMap<LoginChannel, ILogin> iLoginHashMap = new HashMap<>();

    public LoginService(UserRepository userRepository) {this.userRepository = userRepository;}

    @Start
    public void start(SpringUtil springUtil) {
        springUtil.getBeans()
                .stream()
                .filter(bean -> bean instanceof ILogin)
                .map(v -> (ILogin) v)
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
     * 登录,如果不存在会直接创建
     *
     * @param userName 账户
     * @param token    密钥
     * @param params   其他参数
     * @return 返回
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-09-08 09:07
     */
    public User find(String userName, String token, JSONObject params) {
        final String openId = Md5Util.md5DigestEncode(userName, PRIVATE_TOKEN);
        synchronized (openId) {
            Optional<User> byId = userRepository.findById(openId);
            if (byId.isPresent()) {
                return byId.get();
            }
            User user = new User();
            user.setOpenId(openId)
                    .setAccount(userName)
                    .setToken(token)
                    .setOther(params);
            userRepository.save(user);
            return user;
        }
    }

}
