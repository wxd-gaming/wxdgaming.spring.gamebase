package wxdgaming.spring.gamebase.entity.cfg;


import lombok.Getter;
import wxdgaming.spring.boot.data.excel.store.DataTable;
import wxdgaming.spring.gamebase.entity.cfg.bean.QAchieve;

import java.io.Serializable;
import java.util.Map;


/**
 * excel 构建 成就集合, ../cfg/任务成就.xlsx, q_achieve,
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-12 20:17:09
 **/
@Getter
public class QAchieveTable extends DataTable<QAchieve> implements Serializable {

    @Override public void initDb() {
        /*todo 实现一些数据分组*/

    }

    @Override public void checkData(Map<Class<?>, DataTable<?>> store) {
        /*todo 实现数据校验 */
    }

}