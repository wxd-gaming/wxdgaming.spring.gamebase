package wxdgaming.spring.gamebase.game.script.module.gm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.threading.DefaultExecutor;
import wxdgaming.spring.boot.core.threading.Event;
import wxdgaming.spring.boot.core.timer.MyClock;
import wxdgaming.spring.gamebase.game.server.GameStart;

import java.util.concurrent.TimeUnit;

/**
 * gm 命令
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-11 19:49
 **/
@Slf4j
@Controller
@RequestMapping("/gm")
public class GMController implements InitPrint {

    @Autowired private DefaultExecutor defaultExecutor;

    private final long time = MyClock.millis();

    @ResponseBody
    @RequestMapping("reload")
    public String reload() throws Exception {
        // defaultExecutor.schedule(new Event() {
        //     @Override public void onEvent() throws Throwable {
                GameStart.reloadScript();
        //     }
        // }, 100, TimeUnit.MILLISECONDS);
        return "ok " + time;
    }

}
