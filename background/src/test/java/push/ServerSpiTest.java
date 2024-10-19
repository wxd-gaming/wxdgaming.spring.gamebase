package push;

import org.junit.Test;
import wxdgaming.spring.boot.core.lang.RandomUtils;

/**
 * 游戏接口测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-18 20:54
 **/
public class ServerSpiTest extends TestBase {

    @Test
    public void serverList() {
        String url = "http://127.0.0.1:28081/gameinfo/list";
        String result = httpClientService.doPostText(url)
                .addRequestHeader("token", getToken())
                .addRequestParam("gameId", 1)
                .request()
                .bodyString();
        System.out.println(result);
    }


    @Test
    public void pushServer() {
        for (int i = 1; i <= 5; i++) {
            pushServer(i, "360");
            pushServer(i, "yy");
            pushServer(i, "腾讯");
            pushServer(i, "华为");
        }
    }

    void pushServer(int gameId, String platform) {
        String string = null;
        int random = RandomUtils.random(10, 400);
        for (int i = 1; i <= random; i++) {

            string = httpClientService.doPostJson(
                            "http://localhost:28081/server/push",
                            """
                                    {
                                    "gameId": %s,
                                    "platform": "%s",
                                    "sid": %s,
                                    "name": "%s服",
                                    "showName": "%s服",
                                    "openTime": "2099-01-01 00:00:00",
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
                                    """.formatted(gameId, platform, i, i, i)
                    )
                    .addRequestHeader("token", getToken())
                    .request()
                    .bodyString();
        }
        System.out.println(string);
    }
}
