package wxdgaming.spring.gamebase.entity.cfg.bean;


import lombok.Getter;
import wxdgaming.spring.boot.data.excel.store.DataChecked;
import wxdgaming.spring.gamebase.entity.cfg.bean.mapping.QPlayerMapping;

import java.io.Serializable;


/**
 * excel 构建 怪物表, ../cfg/玩家信息.xlsx, q_player,
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-12 20:17:09
 **/
@Getter
public class QPlayer extends QPlayerMapping implements Serializable, DataChecked {

    @Override public void initAndCheck() throws Exception {
        /*todo 实现数据检测和初始化*/

    }

}
