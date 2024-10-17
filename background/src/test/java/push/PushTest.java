package push;

import org.junit.Before;
import org.junit.Test;
import wxdgaming.spring.boot.core.threading.ExecutorBuilder;
import wxdgaming.spring.boot.core.util.StringsUtil;
import wxdgaming.spring.boot.webclient.HttpClientBuild;
import wxdgaming.spring.boot.webclient.HttpClientService;

/**
 * 上报游戏
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-15 15:27
 **/
public class PushTest {

    HttpClientService httpClientService;

    @Test
    public void t0() {
        System.out.println(StringsUtil.getRandomString(32));
    }

    @Before
    public void before() {
        ExecutorBuilder executorBuilder = new ExecutorBuilder();
        HttpClientBuild httpClientBuild = new HttpClientBuild();
        httpClientService = new HttpClientService(executorBuilder.virtualExecutor(), httpClientBuild.httpClient());
    }

    @Test
    public void pushGame() {
        String string = httpClientService.doPostJson(
                        "http://localhost:28081/gameinfo/push",
                        "{\"name\":\"王者传奇\",\"icon\":\"王者传奇.icon\",\"description\":\"复古传奇的王者传奇\"}"
                )
                .request()
                .bodyString();
        System.out.println(string);
    }

    @Test
    public void pushServer() {

        String string = null;
        for (int i = 0; i < 100; i++) {

            string = httpClientService.doPostJson(
                            "http://localhost:28081/server/push",
                            """
                                    {
                                    "gameId": 1,
                                    "platform": "yy",
                                    "sid": %s,
                                    "name": "yy%s服",
                                    "showName": "yy%s服",
                                    "openTime": "",
                                    "maintainTime": "",
                                    "wlan": "",
                                    "lan": "",
                                    "port": 0,
                                    "webPort": 0,
                                    "status": "",
                                    "version": "",
                                    "registerUserCount": 0,
                                    "registerRoleCount": 0,
                                    "onlineRoleCount": 0,
                                    "rechargeCount": 0,
                                    "updateTime": 0,
                                    "createdTime": 0
                                    }
                                    """.formatted(i + 1, i + 1, i + 1)
                    )
                    .request()
                    .bodyString();
        }
        System.out.println(string);
    }


}
