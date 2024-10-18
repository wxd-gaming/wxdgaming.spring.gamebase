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
        String string = httpClientService.doPostJson(
                        "http://localhost:28081/gameinfo/push",
                        "{\"name\":\"王者传奇\",\"icon\":\"王者传奇.icon\",\"description\":\"复古传奇的王者传奇\"}"
                )
                .addRequestHeader("token", getToken())
                .request()
                .bodyString();
        System.out.println(string);
    }


}
