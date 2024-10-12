package wxdgaming.spring.gamebase.entity;


import wxdgaming.spring.boot.core.collection.MapOf;
import wxdgaming.spring.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 属性类型
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-23 18:23
 **/
public enum AttrType implements IEnum {
    HP(1, "血量"),
    MAXHP(2, "最大血量"),
    体力(3, "体力"),
    攻击(4, "攻击力"),
    防御(5, "防御"),
    ;

    private static final Map<Integer, AttrType> static_map = MapOf.asMap(AttrType::getCode, AttrType.values());

    public static AttrType as(int value) {
        return static_map.get(value);
    }

    private final int code;
    private final String comment;

    AttrType(int code, String comment) {
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