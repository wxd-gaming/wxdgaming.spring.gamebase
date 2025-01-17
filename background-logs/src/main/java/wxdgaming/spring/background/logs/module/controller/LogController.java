package wxdgaming.spring.background.logs.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxdgaming.spring.background.logs.log.Slog;
import wxdgaming.spring.background.logs.module.LogService;

/**
 * 日志接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-17 16:15
 **/
@RestController
@RequestMapping("/log")
public class LogController {

    final LogService logService;

    @Autowired
    public LogController(LogService logService) {this.logService = logService;}

    @RequestMapping("/push")
    public String push(@RequestBody Slog slog) {

        return "ok";
    }

}
