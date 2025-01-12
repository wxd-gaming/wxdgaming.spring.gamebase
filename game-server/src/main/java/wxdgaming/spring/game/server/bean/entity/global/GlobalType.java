package wxdgaming.spring.game.server.bean.entity.global;

import wxdgaming.spring.boot.core.collection.MapOf;
import wxdgaming.spring.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 全局数据类型
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-11 18:25
 **/
public enum GlobalType implements IEnum {
    None(0, "默认值"),
    Rank(1, "排行榜"),
    ;

    private static final Map<Integer, GlobalType> static_map = MapOf.asMap(GlobalType::getCode, GlobalType.values());

    public static GlobalType of(int value) {
        return static_map.get(value);
    }

    public static GlobalType ofOrException(int value) {
        GlobalType tmp = static_map.get(value);
        if (tmp == null) throw new RuntimeException("查找失败 " + value);
        return tmp;
    }

    private final int code;
    private final String comment;

    GlobalType(int code, String comment) {
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