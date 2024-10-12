package wxdgaming.spring.gamebase.entity.cfg.bean.mapping;


import lombok.Getter;
import lombok.Setter;
import wxdgaming.spring.boot.core.lang.ObjectBase;
import wxdgaming.spring.boot.data.excel.store.DataKey;
import wxdgaming.spring.boot.data.excel.store.DataMapping;

import java.io.Serializable;
import java.util.*;

/**
 * excel 构建 任务集合, ../cfg/任务成就.xlsx, q_task,
 *
 * @author: wxd-gaming(無心道, 15388152619)
 **/
@Getter
@Setter
@DataMapping(name = "q_task", comment = "任务集合", excelPath = "../cfg/任务成就.xlsx", sheetName = "q_task")
public abstract class QTaskMapping extends ObjectBase implements Serializable, DataKey {

    /** 主键id */
    protected int id;
    /** 任务类型 */
    protected wxdgaming.spring.gamebase.entity.TaskType taskType;
    /** 上一个任务ID */
    protected int before;
    /** 下一个任务ID */
    protected int after;
    /** 任务名称 */
    protected String name;
    /** 任务说明 */
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
