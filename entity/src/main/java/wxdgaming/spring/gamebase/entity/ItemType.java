package wxdgaming.spring.gamebase.entity;


import wxdgaming.spring.boot.core.collection.MapOf;
import wxdgaming.spring.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 道具类型
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-11-07 20:16
 **/
public enum ItemType implements IEnum {

    None(0, ItemGroup.None, "默认值"),
    CURRENCY(1, ItemGroup.CURRENCY, "货币"),
    Exp(2, ItemGroup.CURRENCY, "经验值"),
    LV(3, ItemGroup.CURRENCY, "等级丹"),
    EQUIP(1000, ItemGroup.EQUIP, "装备"),
    ;

    private static final Map<Integer, ItemType> static_map = MapOf.asMap(ItemType::getCode, ItemType.values());

    public static ItemType as(int value) {
        return static_map.get(value);
    }

    private final int code;
    private final ItemGroup itemGroup;
    private final String comment;

    ItemType(int code, ItemGroup itemGroup, String comment) {
        this.code = code;
        this.itemGroup = itemGroup;
        this.comment = comment;
    }

    @Override
    public int getCode() {
        return code;
    }

    public ItemGroup getItemGroup() {
        return itemGroup;
    }

    @Override
    public String getComment() {
        return comment;
    }
}