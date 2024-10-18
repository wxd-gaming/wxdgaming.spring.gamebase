package push;

import org.junit.Test;

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
                    .addRequestHeader("token", getToken())
                    .request()
                    .bodyString();
        }
        System.out.println(string);
    }
}
