package wxdgaming.spring.gamebase.membership.module.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.io.Objects;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.core.threading.ThreadContext;
import wxdgaming.spring.boot.core.util.JwtUtils;
import wxdgaming.spring.boot.core.util.StringsUtil;
import wxdgaming.spring.gamebase.membership.CheckSign;
import wxdgaming.spring.gamebase.membership.MembershipService;
import wxdgaming.spring.gamebase.membership.entity.bean.Account;
import wxdgaming.spring.gamebase.membership.entity.store.AccountRepository;
import wxdgaming.spring.gamebase.membership.module.user.UserService;

import java.util.Date;
import java.util.List;
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

    @Autowired MembershipService membershipService;
    @Autowired UserService userService;
    @Autowired AccountRepository accountRepository;

    @CheckSign(isRoot = true)
    @RequestMapping("/list")
    public RunResult list(HttpServletRequest request, @RequestParam(name = "search", required = false) String search) {
        List<Account> list = accountRepository.findAll().stream()
                .filter(account ->
                        account.getName().contains(search)
                        || account.getMobile().contains(search)
                )
                .toList();
        return RunResult.ok().data(list);
    }

    @ResponseBody
    @PostMapping(value = "/check")
    public RunResult check(HttpServletRequest httpServletRequest) {
        Account loginAccount = ThreadContext.context(Account.class);
        if (loginAccount != null) {
            return login(loginAccount);
        }
        return RunResult.error("");
    }

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

        password = membershipService.password(account.getUid(), accountName, password);

        if (!Objects.equals(account.getPassword(), password)) {
            return RunResult.error("密码错误");
        }

        return login(account);
    }

    RunResult login(Account account) {
        String compact = JwtUtils.createJwt().header().add("type", "account").and()
                .expiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7)))
                .claim("account", account.getUid().toString())
                .claim("accountName", account.getName())
                .compact();

        return RunResult.ok().data(compact);
    }


}
