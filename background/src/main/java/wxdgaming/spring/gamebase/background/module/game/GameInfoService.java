package wxdgaming.spring.gamebase.background.module.game;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.gamebase.background.entity.bean.GameInfo;
import wxdgaming.spring.gamebase.background.entity.store.GameInfoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 区服信息服务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-14 13:55
 **/
@Getter
@Service()
public class GameInfoService {

    @Autowired private GameInfoRepository gameInfoRepository;
    private final Map<Integer, GameInfo> gameInfoMap = new ConcurrentSkipListMap<>();

    @PostConstruct
    public void initialize() {
        List<GameInfo> all = gameInfoRepository.findAll();
        all.forEach(gameInfo -> gameInfoMap.put(gameInfo.getUid(), gameInfo));
    }

    public Collection<GameInfo> list() {
        return gameInfoMap.values();
    }

    public GameInfo get(int uid) {
        return gameInfoMap.get(uid);
    }

}
