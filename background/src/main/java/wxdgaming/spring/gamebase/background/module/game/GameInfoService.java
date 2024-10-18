package wxdgaming.spring.gamebase.background.module.game;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.util.StringsUtil;
import wxdgaming.spring.gamebase.background.entity.bean.GameInfo;
import wxdgaming.spring.gamebase.background.entity.store.GameInfoRepository;

import java.util.Collection;
import java.util.List;

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

    @PostConstruct
    public void initialize() {
        List<GameInfo> all = gameInfoRepository.findAll();
        all.forEach(gameInfo -> {
            if (StringsUtil.emptyOrNull(gameInfo.getAppKey())) {
                gameInfo.setAppKey(StringsUtil.getRandomString(32));
                save(gameInfo);
            }
            if (StringsUtil.emptyOrNull(gameInfo.getRechargeKey())) {
                gameInfo.setRechargeKey(StringsUtil.getRandomString(32));
                save(gameInfo);
            }
        });
    }

    public void save(GameInfo gameInfo) {
        gameInfoRepository.saveAndFlush(gameInfo);
    }

    public Collection<GameInfo> list() {
        return gameInfoRepository.findAll();
    }

    public GameInfo get(int uid) {
        return gameInfoRepository.findById(uid).orElse(null);
    }

}
