package wxdgaming.spring.gamebase.game.script;

import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.lang.RandomUtils;
import wxdgaming.spring.boot.net.SocketSession;
import wxdgaming.spring.boot.net.client.TcpSocketClient;
import wxdgaming.spring.boot.rpc.RpcService;
import wxdgaming.spring.gamebase.game.server.client.LoginClientBuild;

/**
 * s
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-16 13:01
 **/
@Slf4j
@Controller
@RequestMapping(path = "/script")
public class ScheduledTest implements InitPrint {

    @Autowired RpcService rpcService;
    @Autowired TcpSocketClient loginClient;

    @Autowired LoginClientBuild loginClientBuild;
    @Autowired private SpringUtil springUtil;

    public ScheduledTest() {
        System.out.println("==================================================================================");
    }


    @PostConstruct
    public void test() {
        System.out.println(loginClientBuild);
    }

    @Bean
    public String loginKey() {
        System.out.println("------------------------------------------------------");
        return "dddddddddddddddddd";
    }

    @ResponseBody
    @RequestMapping("/test")
    public String test(HttpServletRequest httpServletRequest) {
        return "ok " + springUtil.getBean("loginKey");
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void postOnly() {
        if (loginClient != null) {
            SocketSession session = loginClient.idleSession();
            if (session == null) {
                log.info("login server session null {}", this.hashCode());
                return;
            }
            rpcService.request(
                    session,
                    "/game/online",
                    new JSONObject().fluentPut("sid", 1).fluentPut("onlineSize", RandomUtils.random(30000))
            );
        }
    }

}
