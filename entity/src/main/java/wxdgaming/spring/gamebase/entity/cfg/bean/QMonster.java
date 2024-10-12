package wxdgaming.spring.gamebase.entity.cfg.bean;


import lombok.Getter;
import wxdgaming.spring.boot.data.excel.store.DataChecked;
import wxdgaming.spring.gamebase.entity.cfg.bean.mapping.QMonsterMapping;

import java.io.Serializable;


/**
 * excel 构建 怪物表, ../cfg/怪物信息.xlsx, q_monster,
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-12 20:17:08
 **/
@Getter
public class QMonster extends QMonsterMapping implements Serializable, DataChecked {

    @Override public void initAndCheck() throws Exception {
        /*todo 实现数据检测和初始化*/

    }

}
