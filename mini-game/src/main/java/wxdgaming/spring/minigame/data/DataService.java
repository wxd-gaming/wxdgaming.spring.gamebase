package wxdgaming.spring.minigame.data;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据服务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-12-16 15:25
 **/
public class DataService {

    private final ConcurrentHashMap<Integer, ServerDataCenter> serverDataMap = new ConcurrentHashMap<>();

    public ServerDataCenter getServerData(int serverId) {
        return serverDataMap.get(serverId);
    }

    public void addServerData(int serverId, ServerDataCenter serverDataCenter) {
        serverDataMap.put(serverId, serverDataCenter);
    }

}
