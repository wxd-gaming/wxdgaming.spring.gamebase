package wxdgaming.spring.gamebase.membership;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.util.Md5Util;
import wxdgaming.spring.gamebase.membership.entity.bean.GlobalData;
import wxdgaming.spring.gamebase.membership.entity.store.GlobalRepository;

import java.io.Closeable;
import java.util.Optional;

/**
 * 后台服务中心
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-18 11:39
 **/
@Getter
@Service
public class MembershipService implements InitPrint, AutoCloseable, Closeable {

    @Autowired GlobalRepository globalRepository;
    private GlobalData globalData;

    private static final String TAG = "B975C8FC276637AAF4EA6B9C04FE02BB";

    @PostConstruct
    public void initialize() {
        Optional<GlobalData> byId = globalRepository.findById(1);
        byId.ifPresentOrElse(
                find -> {
                    globalData = find;
                },
                () -> {
                    globalData = new GlobalData();
                    saveAndFlush();
                }
        );
    }

    public void saveAndFlush() {
        globalRepository.saveAndFlush(globalData);
    }

    @Override public void close() {
        saveAndFlush();
    }

    public String password(long uid, String account, String pwd) {
        return Md5Util.md5DigestEncode(String.valueOf(uid), account, TAG, pwd);
    }

}
