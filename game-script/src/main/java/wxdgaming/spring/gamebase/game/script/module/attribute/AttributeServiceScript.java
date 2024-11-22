package wxdgaming.spring.gamebase.game.script.module.attribute;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.InitPrint;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.ann.Start;
import wxdgaming.spring.boot.core.collection.Table;
import wxdgaming.spring.boot.core.json.FastJsonUtil;
import wxdgaming.spring.boot.core.util.AssertUtil;
import wxdgaming.spring.gamebase.entity.AttrInfo;
import wxdgaming.spring.gamebase.entity.AttrType;
import wxdgaming.spring.gamebase.entity.ModuleGroup;
import wxdgaming.spring.gamebase.game.script.module.event.ILogin;
import wxdgaming.spring.gamebase.game.script.module.rank.RankScript;
import wxdgaming.spring.gamebase.game.server.bean.MapObject;
import wxdgaming.spring.gamebase.game.server.bean.entity.user.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 属性计算器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-11-21 16:32
 **/
@Slf4j
@Service
public class AttributeServiceScript implements InitPrint, ILogin {

    final RankScript rankScript;


    /** 根据对象类型计算器分组 */
    private final Table<MapObject.ObjectType, ModuleGroup, ICalculator> calculatorTable = new Table<>();

    public AttributeServiceScript(RankScript rankScript) {
        this.rankScript = rankScript;
    }

    @Start
    @Order(99)
    public void initImpl(SpringUtil springUtil) {
        Stream<ICalculator> beansOfType = springUtil.getBeansOfType(ICalculator.class);
        beansOfType.forEach(iCalculator -> {
            for (MapObject.ObjectType objectType : iCalculator.objectType()) {
                ICalculator old = calculatorTable.put(objectType, iCalculator.attrGroup(), iCalculator);
                AssertUtil.assertTrueFmt(old == null, "重复注册计算器", "");
            }
        });
    }

    @Order(9999999)
    @Override public void onLogin(Player player) {

    }

    /** 计算所有 */
    public void countCalculator(MapObject object) {
        ModuleGroup[] values = ModuleGroup.values();
        for (ModuleGroup moduleGroup : values) {
            countCalculator0(object, moduleGroup);
        }
        finalCalculator(object, null);
    }

    /** 单个模块 */
    public void countCalculator(MapObject object, ModuleGroup moduleGroup) {
        countCalculator0(object, moduleGroup);
        finalCalculator(object, moduleGroup);
    }

    /** 重新计算某一模块的属性 */
    void countCalculator0(MapObject object, ModuleGroup moduleGroup) {
        ICalculator iCalculator = calculatorTable.get(object.getObjectType(), moduleGroup);
        if (iCalculator != null) {
            {
                HashMap<AttrType, Long> map = object.getGroupAttrMap().computeIfAbsent(moduleGroup, l -> new AttrInfo());
                map.clear();
                iCalculator.calculator(object, map);
            }
            {
                HashMap<AttrType, Long> map = object.getGroupAttrProMap().computeIfAbsent(moduleGroup, l -> new AttrInfo());
                map.clear();
                iCalculator.calculatorPro(object, map);
            }
        }
    }

    void finalCalculator(MapObject object, ModuleGroup moduleGroup) {

        object.getFinalAttrMap().clear();
        object.getGroupAttrMap().values().forEach(v -> {
            for (Map.Entry<AttrType, Long> entry : v.entrySet()) {
                object.getFinalAttrMap().merge(entry.getKey(), entry.getValue(), Math::addExact);
            }
        });

        HashMap<AttrType, Long> tempPro = new HashMap<>();
        object.getGroupAttrProMap().values().forEach(v -> {
            for (Map.Entry<AttrType, Long> entry : v.entrySet()) {
                tempPro.merge(entry.getKey(), entry.getValue(), Math::addExact);
            }
        });

        for (Map.Entry<AttrType, Long> entry : tempPro.entrySet()) {
            object.getFinalAttrMap().merge(entry.getKey(), entry.getValue(), (v1, v2) -> (v1 * (10000 + v2)) / 10000);
        }
        /*计算战斗力*/
        long power = object.getFinalAttrMap().values().stream().mapToLong(v -> v * 100).sum();
        if (object.getPower() != power) {
            if (object.getObjectType() == MapObject.ObjectType.Player) {
                log.info(
                        "{}, 战斗力：{} , 历史：{} - {}",
                        object, object.getPower(), power, object.getMaxPower()
                );
            }
            object.setPower(power);
            object.setMaxPower(Math.max(object.getMaxPower(), power));

            if (log.isDebugEnabled() && object instanceof Player player) {
                log.info("{}, 属性：{}", object, FastJsonUtil.toJson(object.getFinalAttrMap()));
                {
                    // rankScript.updatePower(player);
                }
            }
        }
        long maxHp = object.getFinalAttrMap().getOrDefault(AttrType.MAXHP, 0L);
        if (object.getHp() > maxHp) {
            object.setHp(maxHp);
        }
    }

}
