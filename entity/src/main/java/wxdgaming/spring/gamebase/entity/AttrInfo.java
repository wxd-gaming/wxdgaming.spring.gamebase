package wxdgaming.spring.gamebase.entity;


import java.util.HashMap;

/**
 * 属性计算器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-23 20:39
 **/
public class AttrInfo extends HashMap<AttrType, Long> {

    @Override public Long get(Object key) {
        return super.getOrDefault(key, 0L);
    }

}
