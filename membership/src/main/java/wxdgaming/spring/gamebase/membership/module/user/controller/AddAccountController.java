package wxdgaming.spring.gamebase.membership.module.user.controller;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wxdgaming.spring.boot.core.io.Objects;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.core.threading.ThreadContext;
import wxdgaming.spring.boot.core.timer.MyClock;
import wxdgaming.spring.boot.core.util.StringsUtil;
import wxdgaming.spring.gamebase.membership.CheckSign;
import wxdgaming.spring.gamebase.membership.MembershipService;
import wxdgaming.spring.gamebase.membership.entity.bean.Account;
import wxdgaming.spring.gamebase.membership.entity.store.AccountRepository;
import wxdgaming.spring.gamebase.membership.module.user.UserService;

import java.util.List;

/**
 * 添加账号
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-18 13:30
 **/
@Slf4j
@RestController
@RequestMapping("/account")
public class AddAccountController {

    private final UserService userService;
    private final AccountRepository accountRepository;
    private final MembershipService membershipService;

    @Autowired
    public AddAccountController(UserService userService, AccountRepository accountRepository, MembershipService membershipService) {
        this.userService = userService;
        this.accountRepository = accountRepository;
        this.membershipService = membershipService;
    }

    @PostConstruct
    public void initialize() {
        String wxdgaming = "wxdgaming";
        String password = "87FE6B";// StringsUtil.randomUuid16("", 6);
        if (!userService.exists(wxdgaming)) {
            Account root = new Account();
            root.setUid(membershipService.getGlobalData().getAccountNewId().incrementAndGet());
            root
                    .setName(wxdgaming)
                    .setName2(wxdgaming)
                    .setNick("超级管理员")
                    .setPassword(membershipService.password(root.getUid(), root.getName(), password))
                    .setRoot(true)
                    .setRoot2(true)
                    .setRoot3(true)
                    .setEmail("wxd-gaming@qq.com")
                    .setMobile("15388152619")
                    .setCreatedTime(MyClock.nowString())
            ;
            accountRepository.saveAndFlush(root);
            membershipService.saveAndFlush();
            log.warn("root pwd: {}", password);
        }
    }

    @CheckSign
    @RequestMapping("/add")
    public RunResult addAccount(HttpServletRequest request, @RequestBody Account account) {
        Account loginAccount = ThreadContext.context(Account.class);

        if (!loginAccount.isRoot()
            && !loginAccount.isRoot2()
            && !loginAccount.isRoot3()) {
            return RunResult.error("权限不足，不能添加子账号");
        }
        if (StringsUtil.emptyOrNull(account.getName()) || account.getName().length() < 2) {
            return RunResult.error("账号不足6位");
        }

        /*纯小写*/
        account.setName(account.getName().trim().toLowerCase());

        if (StringsUtil.checkMatches(account.getName(), StringsUtil.PATTERN_DB)) {
            return RunResult.error("账号不允许有特殊字符");
        }

        if (Objects.equals("root", account.getName())) {
            return RunResult.error("账号不能使用关键字");
        }

        if (userService.exists(account.getName())) {
            return RunResult.error("账号重复");
        }

        if (StringsUtil.emptyOrNull(account.getPassword()) || account.getPassword().length() < 6) {
            return RunResult.error("密码强度不足6位");
        }
        if (!loginAccount.isRoot()) {
            account.setRoot(false);
            account.setRoot2(false);
        }

        if (!loginAccount.isRoot2()) {
            account.setRoot3(false);
        }

        account.setUid(membershipService.getGlobalData().getAccountNewId().incrementAndGet());
        account.setForAccountId(loginAccount.getUid());
        /*md5加密*/
        account.setPassword(membershipService.password(account.getUid(), account.getName(), account.getPassword()));
        account.setCreatedTime(MyClock.nowString());
        accountRepository.saveAndFlush(account);
        membershipService.saveAndFlush();

        return RunResult.ok();
    }

}
