package wxdgaming.spring.game.server.bean;

import lombok.Getter;
import lombok.Setter;
import wxdgaming.spring.boot.core.lang.ObjectBase;

import java.util.HashMap;
import java.util.Map;

/**
 * 属性集合
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-09 20:52
 **/
@Getter
@Setter
public class AttributeInfo extends ObjectBase {

    private final HashMap<AttributeType, Long> map = new HashMap<>();
    private final HashMap<AttributeType, Long> proMap = new HashMap<>();
    private long fightPower = 0;

    public void add(AttributeType type, long value) {
        map.merge(type, value, Math::addExact);
    }

    public void addPro(AttributeType type, long value) {
        proMap.merge(type, value, Math::addExact);
    }

    public void add(AttributeInfo attributeInfo) {
        for (Map.Entry<AttributeType, Long> entry : map.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
    }

    public void addPro(AttributeInfo attributeInfo) {
        for (Map.Entry<AttributeType, Long> entry : map.entrySet()) {
            addPro(entry.getKey(), entry.getValue());
        }
    }

}
