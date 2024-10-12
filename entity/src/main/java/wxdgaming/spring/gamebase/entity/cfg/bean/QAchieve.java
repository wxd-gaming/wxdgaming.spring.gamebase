package wxdgaming.spring.gamebase.entity.cfg.bean;


import lombok.Getter;
import wxdgaming.spring.boot.data.excel.store.DataChecked;
import wxdgaming.spring.gamebase.entity.cfg.bean.mapping.QAchieveMapping;

import java.io.Serializable;


/**
 * excel 构建 成就集合, ../cfg/任务成就.xlsx, q_achieve,
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-12 20:17:09
 **/
@Getter
public class QAchieve extends QAchieveMapping implements Serializable, DataChecked {

    @Override public void initAndCheck() throws Exception {
        /*todo 实现数据检测和初始化*/

    }

}
