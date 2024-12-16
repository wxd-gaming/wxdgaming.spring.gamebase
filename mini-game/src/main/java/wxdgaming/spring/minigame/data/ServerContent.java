package wxdgaming.spring.minigame.data;

import lombok.Getter;

/**
 * 当前区服上下文
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-12-16 15:12
 **/
@Getter
public class ServerContent {

    private final int sid;
    private final DataService dataService;

    public ServerContent(int sid, DataService dataService) {
        this.sid = sid;
        this.dataService = dataService;
    }

    public ServerDataCenter serverDataCenter() {
        return dataService.getServerData(this.getSid());
    }

}
