package wxdgaming.spring.gamebase.login.module.login;

import wxdgaming.spring.boot.core.collection.MapOf;
import wxdgaming.spring.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 渠道
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-08 08:28
 **/
public enum LoginChannel implements IEnum {
    None(0, "默认值"),
    Local(100, "本地"),
    Quick(200, "易接sdk"),
    ;

    private static final Map<Integer, LoginChannel> static_map = MapOf.asMap(LoginChannel::getCode, LoginChannel.values());

    public static LoginChannel of(int value) {
        return static_map.get(value);
    }

    public static LoginChannel ofOrException(int value) {
        LoginChannel tmp = static_map.get(value);
        if (tmp == null) throw new RuntimeException("查找失败 " + value);
        return tmp;
    }

    private final int code;
    private final String comment;

    LoginChannel(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getComment() {
        return comment;
    }
}