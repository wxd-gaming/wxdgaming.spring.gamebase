package push;

import org.junit.Test;

/**
 * 游戏接口测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-18 20:54
 **/
public class GameSpiTest extends TestBase {

    @Test
    public void gameList() {
        String url = "http://127.0.0.1:28081/gameinfo/list";
        String result = httpClientService.doPostText(url)
                .addRequestHeader("token", getToken())
                .request()
                .bodyString();
        System.out.println(result);
    }


    @Test
    public void pushGame() {
        pushGame("王者传奇");
        pushGame("金铲铲");
        pushGame("复古奇迹");
        pushGame("向僵尸开炮");
        pushGame("热血大明");
    }

    public void pushGame(String name) {

        String string = httpClientService.doPostJson(
                        "http://localhost:28081/gameinfo/push",
                        """
                                {"name":"%s","icon":"%s.icon","description":"%s%s%s%s"}""".formatted(name, name, name, name, name, name)
                )
                .addRequestHeader("token", getToken())
                .request()
                .bodyString();
        System.out.println(string);
    }


}
