package wxdgaming.spring.gamebase.entity.cfg.bean;


import lombok.Getter;
import wxdgaming.spring.boot.data.excel.store.DataChecked;
import wxdgaming.spring.gamebase.entity.cfg.bean.mapping.QItemMapping;

import java.io.Serializable;


/**
 * excel 构建 任务集合, ../cfg/道具.xlsx, q_item,
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-12 20:17:09
 **/
@Getter
public class QItem extends QItemMapping implements Serializable, DataChecked {

    @Override public void initAndCheck() throws Exception {
        /*todo 实现数据检测和初始化*/

    }

}
