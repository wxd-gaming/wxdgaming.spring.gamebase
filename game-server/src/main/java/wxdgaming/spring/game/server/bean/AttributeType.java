package wxdgaming.spring.game.server.bean;

import wxdgaming.spring.boot.core.collection.MapOf;
import wxdgaming.spring.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 属性类型
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-10 09:03
 **/
public enum AttributeType implements IEnum {
    None(0, "默认值"),
    ATTACK(1, "攻击"),
    DEFENSE(2, "防御"),
    HP(3, "生命"),
    MP(4, "魔法"),
    MAX_HP(5, "最大生命"),
    MAX_MP(6, "最大魔法"),
    ;

    private static final Map<Integer, AttributeType> static_map = MapOf.asMap(AttributeType::getCode, AttributeType.values());

    public static AttributeType of(int value) {
        return static_map.get(value);
    }

    public static AttributeType ofOrException(int value) {
        AttributeType tmp = static_map.get(value);
        if (tmp == null) throw new RuntimeException("查找失败 " + value);
        return tmp;
    }

    private final int code;
    private final String comment;

    AttributeType(int code, String comment) {
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