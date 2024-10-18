package wxdgaming.spring.gamebase.background.module.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.io.Objects;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.core.util.JwtUtils;
import wxdgaming.spring.boot.core.util.StringsUtil;
import wxdgaming.spring.gamebase.background.BackendService;
import wxdgaming.spring.gamebase.background.entity.bean.Account;
import wxdgaming.spring.gamebase.background.module.user.UserService;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 用户接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-18 13:29
 **/
@RestController
@RequestMapping("/account")
public class UserController implements InitPrint {

    @Autowired BackendService backendService;
    @Autowired UserService userService;

    @ResponseBody
    @PostMapping(value = "/login")
    public RunResult login(HttpServletRequest httpServletRequest,
                           @RequestParam("accountName") String accountName,
                           @RequestParam("password") String password) {

        accountName = accountName.trim().toLowerCase();

        if (StringsUtil.checkMatches(accountName, StringsUtil.PATTERN_DB)) {
            return RunResult.error("账号不允许有特殊字符");
        }

        Account account = userService.find(accountName);
        if (account == null) {
            return RunResult.error("账号错误");
        }

        password = backendService.password(account.getUid(), accountName, password);

        if (!Objects.equals(account.getPassword(), password)) {
            return RunResult.error("密码错误");
        }

        String compact = JwtUtils.createJwt().header().add("type", "account").and()
                .expiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7)))
                .claim("account", account.getUid().toString())
                .claim("accountName", account.getName())
                .compact();

        return RunResult.ok().data(compact);
    }

}
