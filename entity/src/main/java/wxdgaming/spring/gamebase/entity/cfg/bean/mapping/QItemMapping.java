package wxdgaming.spring.gamebase.entity.cfg.bean.mapping;


import lombok.Getter;
import lombok.Setter;
import wxdgaming.spring.boot.core.lang.ObjectBase;
import wxdgaming.spring.boot.data.excel.store.DataKey;
import wxdgaming.spring.boot.data.excel.store.DataMapping;

import java.io.Serializable;
import java.util.*;

/**
 * excel 构建 任务集合, ../cfg/道具.xlsx, q_item,
 *
 * @author: wxd-gaming(無心道, 15388152619)
 **/
@Getter
@Setter
@DataMapping(name = "q_item", comment = "任务集合", excelPath = "../cfg/道具.xlsx", sheetName = "q_item")
public abstract class QItemMapping extends ObjectBase implements Serializable, DataKey {

    /** 主键id */
    protected int id;
    /** 道具类型类型 */
    protected wxdgaming.spring.gamebase.entity.ItemType itemType;
    /** 名字 */
    protected String name;
    /** 说明 */
    protected String description;
    /** 道具的等级 */
    protected int lv;
    /** 叠加上限，0表示无限制 */
    protected int maxCount;
    /** 附加参数 */
    protected int param1;
    /** 附加参数 */
    protected int param2;
    /** 附加参数 */
    protected int param3;
    /** 附加参数如果是装备这个位置是穿戴部位 */
    protected int param4;
    /** 属性 */
    protected wxdgaming.spring.gamebase.entity.AttrInfo attr;

    public Object key() {
        return id;
    }

}
