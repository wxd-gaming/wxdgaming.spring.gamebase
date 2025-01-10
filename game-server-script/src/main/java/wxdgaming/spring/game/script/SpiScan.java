package wxdgaming.spring.game.script;

import org.springframework.stereotype.Component;
import wxdgaming.spring.boot.core.ann.LogicStart;
import wxdgaming.spring.boot.net.server.SocketService;

/**
 * 接口消息扫描
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-10 17:05
 **/
@Component
public class SpiScan {

    @LogicStart
    public void scan(GameScriptReflect gameScriptReflect, SocketService socketService) {
        socketService.getServerMessageDecode().getDispatcher().scanHandlers(
                gameScriptReflect.getSpringReflectContent(),
                new String[]{SpiScan.class.getPackageName()}
        );
    }

}
