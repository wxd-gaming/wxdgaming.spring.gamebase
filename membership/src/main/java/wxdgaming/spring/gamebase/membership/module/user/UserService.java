package wxdgaming.spring.gamebase.membership.module.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.gamebase.membership.entity.bean.Account;
import wxdgaming.spring.gamebase.membership.entity.store.AccountRepository;

/**
 * 账号服务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-18 09:47
 **/
@Slf4j
@Service
public class UserService implements InitPrint {

    @Autowired private AccountRepository accountRepository;


    public boolean exists(String name) {
        return accountRepository.existsByName(name.trim().toLowerCase());
    }

    public Account find(String name) {
        return accountRepository.findByName(name.trim().toLowerCase());
    }

}
