package push;

import org.junit.Test;
import wxdgaming.spring.boot.webclient.HttpClientService;

/**
 * 上报游戏
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-15 15:27
 **/
public class GameTest {
    HttpClientService httpClientService;

    public void before() {

    }

    @Test
    public void t10() {
        httpClientService.doPostJson(
                "http://localhost:28081/gameinfo/push",
                "{\"name\":\"王者传奇\",\"icon\":\"王者传奇.icon\",\"description\":\"复古传奇的王者传奇\",\"createdTime\":0}"
        );
    }

}
