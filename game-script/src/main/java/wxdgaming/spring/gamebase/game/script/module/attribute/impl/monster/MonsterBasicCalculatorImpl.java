package wxdgaming.spring.gamebase.game.script.module.attribute.impl.monster;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wxdgaming.spring.boot.data.excel.store.DataRepository;
import wxdgaming.spring.gamebase.entity.AttrType;
import wxdgaming.spring.gamebase.entity.ModuleGroup;
import wxdgaming.spring.gamebase.entity.cfg.QMonsterTable;
import wxdgaming.spring.gamebase.entity.cfg.bean.QMonster;
import wxdgaming.spring.gamebase.game.script.module.attribute.ICalculator;
import wxdgaming.spring.gamebase.game.server.bean.MapObject;
import wxdgaming.spring.gamebase.game.server.bean.Monster;

import java.util.HashMap;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-23 19:13
 **/
@Slf4j
@Component
public class MonsterBasicCalculatorImpl implements ICalculator<Monster> {

    @Autowired DataRepository cfgCache;

    /** 计算器分组 */
    @Override public MapObject.ObjectType[] objectType() {
        return new MapObject.ObjectType[]{MapObject.ObjectType.Monster};
    }

    /** 计算器类型 */
    @Override public ModuleGroup attrGroup() {
        return ModuleGroup.BASIC;
    }

    /**
     * 计算器
     *
     * @param object
     */
    @Override public void calculator(Monster object, HashMap<AttrType, Long> attrMap) {
        QMonster dbBean = cfgCache.dataTable(QMonsterTable.class, object.getCfgId());
        attrMap.putAll(dbBean.getAttr());
    }

    /**
     * 计算器 获取翻倍属性
     *
     * @param object
     * @param attrMap
     */
    @Override public void calculatorPro(Monster object, HashMap<AttrType, Long> attrMap) {
        QMonster dbBean = cfgCache.dataTable(QMonsterTable.class, object.getCfgId());
        attrMap.putAll(dbBean.getAttrPro());
    }

}
