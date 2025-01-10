package wxdgaming.spring.game.server.bean;

import wxdgaming.spring.boot.core.collection.MapOf;
import wxdgaming.spring.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 属性模块
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-09 21:02
 **/
public enum AttributeModule implements IEnum {
    None(0, "默认值"),
    ;

    private static final Map<Integer, AttributeModule> static_map = MapOf.asMap(AttributeModule::getCode, AttributeModule.values());

    public static AttributeModule of(int value) {
        return static_map.get(value);
    }

    public static AttributeModule ofOrException(int value) {
        AttributeModule tmp = static_map.get(value);
        if (tmp == null) throw new RuntimeException("查找失败 " + value);
        return tmp;
    }

    private final int code;
    private final String comment;

    AttributeModule(int code, String comment) {
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