package wxdgaming.spring.gamebase.game.script.module.attribute.impl.player;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wxdgaming.spring.boot.data.excel.store.DataRepository;
import wxdgaming.spring.gamebase.entity.AttrType;
import wxdgaming.spring.gamebase.entity.ModuleGroup;
import wxdgaming.spring.gamebase.entity.cfg.QPlayerTable;
import wxdgaming.spring.gamebase.entity.cfg.bean.QPlayer;
import wxdgaming.spring.gamebase.game.script.module.attribute.ICalculator;
import wxdgaming.spring.gamebase.game.server.bean.MapObject;
import wxdgaming.spring.gamebase.game.server.bean.entity.user.Player;

import java.util.HashMap;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-23 19:13
 **/
@Slf4j
@Component
public class BasicCalculatorImpl implements ICalculator<Player> {

    @Autowired DataRepository cfgCache;

    /** 计算器分组 */
    @Override public MapObject.ObjectType[] objectType() {
        return new MapObject.ObjectType[]{MapObject.ObjectType.Player};
    }

    /** 计算器类型 */
    @Override public ModuleGroup attrGroup() {
        return ModuleGroup.BASIC;
    }

    /** 计算器 */
    @Override public void calculator(Player object, HashMap<AttrType, Long> attrMap) {
        QPlayer dbBean = cfgCache.dataTable(QPlayerTable.class, object.playerSummary().getLv());
        attrMap.putAll(dbBean.getAttr());
    }

    /**
     * 计算器 获取翻倍属性
     *
     * @param object
     * @param attrMap
     */
    @Override public void calculatorPro(Player object, HashMap<AttrType, Long> attrMap) {
        QPlayer dbBean = cfgCache.dataTable(QPlayerTable.class, object.playerSummary().getLv());
        attrMap.putAll(dbBean.getAttrPro());
    }
}