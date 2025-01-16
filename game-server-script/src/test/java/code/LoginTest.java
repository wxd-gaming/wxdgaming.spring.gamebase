package code;

import jakarta.annotation.PostConstruct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wxdgaming.spring.boot.core.CoreScan;
import wxdgaming.spring.boot.core.timer.MyClock;
import wxdgaming.spring.boot.core.util.JwtUtils;
import wxdgaming.spring.boot.net.NetScan;
import wxdgaming.spring.boot.net.client.SocketClient;
import wxdgaming.spring.game.server.proto.user.ReqHeart;
import wxdgaming.spring.game.server.proto.user.ReqLogin;

/**
 * 登录测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-14 17:40
 **/
@RunWith(SpringRunner.class)
@SpringBootApplication
@SpringBootTest(classes = {CoreScan.class, NetScan.class, LoginTest.class})
public class LoginTest {

    @Autowired SocketClient socketClient;
    @Autowired TestReflect testReflect;

    @PostConstruct
    public void init() {
        testReflect.content().executorAppStartMethod();
    }

    @Test
    public void login() throws Exception {

        socketClient.getSessionGroup().writeAndFlush(new ReqHeart().setMilli(MyClock.millis()));

        String compact = JwtUtils.createJwtBuilder()
                .claim("openId", "123")
                .compact();
        socketClient.getSessionGroup().writeAndFlush(new ReqLogin().setToken(compact).setParams(""));
        System.in.read();
    }

}
