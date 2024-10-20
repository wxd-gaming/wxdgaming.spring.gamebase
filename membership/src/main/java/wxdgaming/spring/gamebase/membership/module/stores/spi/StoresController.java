package wxdgaming.spring.gamebase.membership.module.stores.spi;

import io.jsonwebtoken.lang.Strings;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.core.threading.ThreadContext;
import wxdgaming.spring.boot.core.timer.MyClock;
import wxdgaming.spring.boot.core.util.StringsUtil;
import wxdgaming.spring.gamebase.membership.CheckSign;
import wxdgaming.spring.gamebase.membership.entity.bean.Account;
import wxdgaming.spring.gamebase.membership.entity.bean.Stores;
import wxdgaming.spring.gamebase.membership.entity.store.StoresRepository;
import wxdgaming.spring.gamebase.membership.module.stores.StoresService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 门店接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-19 21:35
 **/
@Slf4j
@CheckSign()
@RestController
@RequestMapping("/stores")
public class StoresController {

    @Autowired StoresRepository storesRepository;
    @Autowired StoresService storesService;

    @ResponseBody
    @PostMapping("/list")
    public RunResult list(HttpServletRequest httpServletRequest, @RequestParam(name = "search", required = false) String search) {
        Account loginAccount = ThreadContext.context(Account.class);
        Stream<Stores> storesStream = storesRepository.findAll().stream()
                .filter(v -> loginAccount.isRoot() || loginAccount.getStoresList().contains(v.getUid()));
        if (StringsUtil.notEmptyOrNull(search)) {
            storesStream = storesStream.filter(v ->
                    Objects.equals(String.valueOf(v.getUid()), search)
                    || v.getName().contains(search)
                    || v.getLeader().contains(search)
                    || v.getAddress().contains(search)
            );
        }
        List<Stores> list = storesStream.toList();
        return RunResult.ok().data(list);
    }

    @CheckSign(isRoot = true)
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
                    storesRepository.saveAndFlush(old);
                },
                () -> {
                    stores.setCreatedTime(MyClock.nowString());
                    storesRepository.saveAndFlush(stores);
                }
        );

        return RunResult.ok();
    }


}
