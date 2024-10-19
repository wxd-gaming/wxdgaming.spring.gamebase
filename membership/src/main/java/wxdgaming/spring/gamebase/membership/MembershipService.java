package wxdgaming.spring.gamebase.membership;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.util.Md5Util;
import wxdgaming.spring.gamebase.membership.entity.bean.GlobalData;

/**
 * 后台服务中心
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-18 11:39
 **/
@Getter
@Service
public class MembershipService implements InitPrint {

    private GlobalData globalData;

    private static final String TAG = "BackendServicegewhgxfsodifjaweitnasf24352345";

    @PostConstruct
    public void initialize() {
        globalData = new GlobalData();
    }

    public String password(long uid, String account, String pwd) {
        return Md5Util.md5DigestEncode(String.valueOf(uid), account, TAG, pwd);
    }

}
