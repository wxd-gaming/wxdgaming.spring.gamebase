package wxdgaming.spring.gamebase.entity.cfg.bean.mapping;


import lombok.Getter;
import lombok.Setter;
import wxdgaming.spring.boot.core.lang.ObjectBase;
import wxdgaming.spring.boot.data.excel.store.DataKey;
import wxdgaming.spring.boot.data.excel.store.DataMapping;

import java.io.Serializable;
import java.util.*;

/**
 * excel 构建 成就集合, ../cfg/任务成就.xlsx, q_achieve,
 *
 * @author: wxd-gaming(無心道, 15388152619)
 **/
@Getter
@Setter
@DataMapping(name = "q_achieve", comment = "成就集合", excelPath = "../cfg/任务成就.xlsx", sheetName = "q_achieve")
public abstract class QAchieveMapping extends ObjectBase implements Serializable, DataKey {

    /** 主键id */
    protected int id;
    /** 成就类型 */
    protected int type;
    /** 成就名称 */
    protected String name;
    /** 成就说明 */
    protected String description;
    /** 等级 */
    protected int min_lv;
    /** 等级 */
    protected int max_lv;
    /** 任务条件 */
    protected wxdgaming.spring.boot.core.lang.task.Condition condition;
    /** 任务奖励 */
    protected final List<wxdgaming.spring.gamebase.entity.ItemCfg> rewards = new ArrayList<>();

    public Object key() {
        return id;
    }

}
