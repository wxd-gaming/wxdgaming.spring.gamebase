package wxdgaming.spring.gamebase.membership.module.stores.spi;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.gamebase.membership.CheckSign;
import wxdgaming.spring.gamebase.membership.entity.bean.Stores;
import wxdgaming.spring.gamebase.membership.entity.store.StoresRepository;
import wxdgaming.spring.gamebase.membership.module.stores.StoresService;

import java.util.Optional;

/**
 * 门店接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-19 21:35
 **/
@Slf4j
@CheckSign(isRoot = true)
@RestController
@RequestMapping("/stores")
public class StoresController {

    @Autowired StoresRepository storesRepository;
    @Autowired StoresService storesService;

    @ResponseBody
    @PostMapping("/push")
    public RunResult push(HttpServletRequest httpServletRequest, @RequestBody Stores stores) {
        Optional<Stores> oldStores = Optional.empty();
        if (stores.getUid() != null) {
            oldStores = storesRepository.findById(stores.getUid());
        }
        oldStores.ifPresentOrElse(
                (old) -> {
old.setName(stores.getName());
old.setAddress(stores.getAddress());
old.setLv(stores.getLv());
old.setExpTime(stores.getExpTime());
old.setDescription(stores.getDescription());
old.setLeader(stores.getLeader());
                },
                () -> {

                }
        );
        return RunResult.ok();
    }


}
