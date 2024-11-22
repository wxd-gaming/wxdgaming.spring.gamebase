package wxdgaming.spring.gamebase.game.script.module.attribute.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import wxdgaming.spring.gamebase.entity.AttrType;
import wxdgaming.spring.gamebase.entity.ModuleGroup;
import wxdgaming.spring.gamebase.game.script.module.attribute.ICalculator;
import wxdgaming.spring.gamebase.game.server.bean.MapObject;

import java.util.HashMap;

/**
 * 临时属性
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-23 19:13
 **/
@Slf4j
@Component
public class TempCalculatorImpl implements ICalculator<MapObject> {


    /** 计算器分组 */
    @Override public MapObject.ObjectType[] objectType() {
        return new MapObject.ObjectType[]{MapObject.ObjectType.Player, MapObject.ObjectType.Monster};
    }

    /** 计算器类型 */
    @Override public ModuleGroup attrGroup() {
        return ModuleGroup.TEMP;
    }

    /** 计算器 */
    @Override public void calculator(MapObject object, HashMap<AttrType, Long> attrMap) {
        attrMap.putAll(object.getTempAttrMap());
    }

}
