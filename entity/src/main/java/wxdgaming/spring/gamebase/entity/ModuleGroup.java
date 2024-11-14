package wxdgaming.spring.gamebase.entity;

import wxdgaming.spring.boot.core.collection.MapOf;
import wxdgaming.spring.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 属性分组
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-23 18:29
 **/
public enum ModuleGroup implements IEnum {
    BASIC(1, "基础"),
    SCRIPT(2, "脚本"),
    EQUIP(3, "装备"),
    SKILL(4, "技能"),
    BUFF(5, "Buff"),
    /** 附加属性-直接就是具体的数值，比如gm账号什么的 */
    ADD(98, "附加属性"),
    /** 临时属性，比如调试的时候，增加一些临时属性来辅助打怪什么的 */
    TEMP(99, "临时属性"),
    ;

    private static final Map<Integer, ModuleGroup> static_map = MapOf.asMap(ModuleGroup::getCode, ModuleGroup.values());

    public static ModuleGroup as(int value) {
        return static_map.get(value);
    }

    private final int code;
    private final String comment;

    ModuleGroup(int code, String comment) {
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