package wxdgaming.spring.gamebase.background.module.user;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.timer.MyClock;
import wxdgaming.spring.gamebase.background.BackendService;
import wxdgaming.spring.gamebase.background.entity.bean.Account;
import wxdgaming.spring.gamebase.background.entity.store.AccountRepository;

/**
 * 账号服务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-18 09:47
 **/
@Slf4j
@Service
public class UserService implements InitPrint {

    @Autowired private BackendService backendService;
    @Autowired private AccountRepository accountRepository;

    @PostConstruct
    public void initialize() {
        String wxdgaming = "wxdgaming";
        if (!exists(wxdgaming)) {
            Account root = new Account().setName(wxdgaming);
            root.setUid(backendService.getGlobalData().getAccountId().newId());
            String password = "87FE6B";// StringsUtil.randomUuid16("", 6);
            root
                    .setNick("超级管理员")
                    .setPassword(backendService.password(root.getUid(), root.getName(), password))
                    .setRoot(true)
                    .setRoot2(true)
                    .setRoot3(true)
                    .setEmail("root@localhost")
                    .setMobile("12345678901")
                    .setCreatedTime(MyClock.millis())
            ;
            accountRepository.saveAndFlush(root);
            log.warn("pwd: {}", password);
        }
    }

    public boolean exists(String name) {
        return accountRepository.existsByName(name.trim().toLowerCase());
    }

    public Account find(String name) {
        return accountRepository.findByName(name.trim().toLowerCase());
    }

}
