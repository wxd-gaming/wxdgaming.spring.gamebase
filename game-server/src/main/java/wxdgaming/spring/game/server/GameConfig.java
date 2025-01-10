package wxdgaming.spring.game.server;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

/**
 * 游戏配置
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-10 18:01
 **/
@Getter
@Setter
@Configuration
public class GameConfig {

    private boolean debug;
    private int sid;
    private int maxOnline = 500;
}
