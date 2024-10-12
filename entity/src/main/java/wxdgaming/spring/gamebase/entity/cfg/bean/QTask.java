package wxdgaming.spring.gamebase.entity.cfg.bean;


import lombok.Getter;
import wxdgaming.spring.boot.data.excel.store.DataChecked;
import wxdgaming.spring.gamebase.entity.cfg.bean.mapping.QTaskMapping;

import java.io.Serializable;


/**
 * excel 构建 任务集合, ../cfg/任务成就.xlsx, q_task,
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-12 20:17:09
 **/
@Getter
public class QTask extends QTaskMapping implements Serializable, DataChecked {

    @Override public void initAndCheck() throws Exception {
        /*todo 实现数据检测和初始化*/

    }

}
