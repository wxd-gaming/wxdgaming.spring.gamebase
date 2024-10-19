package wxdgaming.spring.gamebase.membership.module.meun.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.core.threading.ThreadContext;
import wxdgaming.spring.boot.core.timer.MyClock;
import wxdgaming.spring.gamebase.membership.CheckSign;
import wxdgaming.spring.gamebase.membership.entity.bean.Account;
import wxdgaming.spring.gamebase.membership.entity.store.StoresRepository;
import wxdgaming.spring.gamebase.membership.module.meun.MenuService;

import java.util.List;

/**
 * 菜单接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-14 17:11
 **/
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired MenuService menuService;
    @Autowired StoresRepository storesRepository;

    @CheckSign
    @RequestMapping("/list")
    public RunResult list() {
        Account loginAccount = ThreadContext.context(Account.class);
        List<JSONObject> list = storesRepository.findAll()
                .stream()
                .filter(s -> loginAccount.isRoot() || loginAccount.getStoresList().contains(s.getUid()))
                .map(menu -> {
                    JSONObject jo = new JSONObject();
                    jo.put("uid", menu.getUid());
                    jo.put("name", menu.getName());
                    jo.put("leader", menu.getLeader());
                    jo.put("address", menu.getAddress());
                    jo.put("lv", menu.getLv());
                    jo.put("phoneNumber", menu.getPhoneNumber());
                    jo.put("expTime", MyClock.formatDate(menu.getExpTime()));
                    jo.put("description", menu.getDescription());
                    return jo;
                })
                .toList();
        return RunResult.ok().data(list);
    }

}
