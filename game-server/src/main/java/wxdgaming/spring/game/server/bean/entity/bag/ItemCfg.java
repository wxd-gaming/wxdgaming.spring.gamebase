package wxdgaming.spring.game.server.bean.entity.bag;

import lombok.Getter;
import lombok.Setter;
import wxdgaming.spring.boot.core.lang.ObjectBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 道具配置
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-15 13:16
 **/
@Getter
@Setter
public class ItemCfg extends ObjectBase {

    /**
     * 道具id|数量|绑定|过期时间(分钟)|职业限制|性别限制|随机权重;道具id|数量|绑定|过期时间(分钟)|职业限制|性别限制|随机权重
     */
    public static List<ItemCfg> of(String cfg) {
        String[] split = cfg.split("[;；]");
        List<ItemCfg> list = new ArrayList<>();
        for (String s : split) {
            String[] split1 = s.split("[|]");
            ItemCfg itemCfg = new ItemCfg();
            itemCfg.setCfgId(Integer.parseInt(split1[0]));
            itemCfg.setCount(Long.parseLong(split1[1]));
            if (split1.length > 2)
                itemCfg.setBind(Integer.parseInt(split1[2]) == 1);

            if (split1.length > 3)
                /*配置分钟*/
                itemCfg.setExpireTime(TimeUnit.MINUTES.toMillis(Long.parseLong(split1[3])));
            if (split1.length > 4)
                itemCfg.setJob(Integer.parseInt(split1[4]));
            if (split1.length > 5)
                itemCfg.setSex(Integer.parseInt(split1[5]));

            if (split1.length > 6)
                itemCfg.setWeight(Integer.parseInt(split1[6]));

            list.add(itemCfg);
        }
        return List.copyOf(list);
    }

    private int cfgId;
    private long count;
    private boolean bind;
    /** 过期时间 */
    private long expireTime;
    /** 职业限制 */
    private int job;
    /** 性别限制 */
    private int sex;
    /** 随机权重 */
    private int weight;

}
