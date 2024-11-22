package wxdgaming.spring.gamebase.game.script.module.attribute;

import wxdgaming.spring.gamebase.entity.AttrType;
import wxdgaming.spring.gamebase.entity.ModuleGroup;
import wxdgaming.spring.gamebase.game.server.bean.MapObject;

import java.util.HashMap;

/**
 * 计算器接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-23 19:10
 **/
public interface ICalculator<T extends MapObject> {

    /** 计算器分组 */
    MapObject.ObjectType[] objectType();

    /** 计算器类型 */
    ModuleGroup attrGroup();

    /** 计算器 */
    default void calculator(T object, HashMap<AttrType, Long> attrMap) {}

    /** 计算器 获取翻倍属性 */
    default void calculatorPro(T object, HashMap<AttrType, Long> attrMap) {}

}
