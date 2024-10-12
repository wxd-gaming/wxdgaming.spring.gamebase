package wxdgaming.spring.gamebase.entity.cfg.bean.mapping;


import lombok.Getter;
import lombok.Setter;
import wxdgaming.spring.boot.core.lang.ObjectBase;
import wxdgaming.spring.boot.data.excel.store.DataKey;
import wxdgaming.spring.boot.data.excel.store.DataMapping;

import java.io.Serializable;
import java.util.*;

/**
 * excel 构建 活动, ../cfg/活动.xlsx, q_activity,
 *
 * @author: wxd-gaming(無心道, 15388152619)
 **/
@Getter
@Setter
@DataMapping(name = "q_activity", comment = "活动", excelPath = "../cfg/活动.xlsx", sheetName = "q_activity")
public abstract class QActivityMapping extends ObjectBase implements Serializable, DataKey {

    /** 活动流水号 */
    protected int id;
    /** 活动类型id */
    protected int type;
    /** 名称 */
    protected String name;
    /** 开启时间 */
    protected wxdgaming.spring.boot.core.timer.CronExpress openTime;

    public Object key() {
        return id;
    }

}
