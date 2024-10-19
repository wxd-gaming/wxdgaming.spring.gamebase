package wxdgaming.spring.gamebase.membership.module.stores;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.gamebase.membership.entity.bean.Stores;
import wxdgaming.spring.gamebase.membership.entity.store.StoresRepository;

/**
 * 门店服务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-19 21:31
 **/
@Slf4j
@Service
public class StoresService implements InitPrint {

    @Autowired StoresRepository storesRepository;

    public void push(Stores stored) {
        storesRepository.save(stored);
    }

}
