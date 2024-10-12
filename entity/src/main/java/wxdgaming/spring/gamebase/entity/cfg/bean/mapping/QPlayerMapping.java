package wxdgaming.spring.gamebase.entity.cfg.bean.mapping;


import lombok.Getter;
import lombok.Setter;
import wxdgaming.spring.boot.core.lang.ObjectBase;
import wxdgaming.spring.boot.data.excel.store.DataKey;
import wxdgaming.spring.boot.data.excel.store.DataMapping;

import java.io.Serializable;
import java.util.*;

/**
 * excel 构建 怪物表, ../cfg/玩家信息.xlsx, q_player,
 *
 * @author: wxd-gaming(無心道, 15388152619)
 **/
@Getter
@Setter
@DataMapping(name = "q_player", comment = "怪物表", excelPath = "../cfg/玩家信息.xlsx", sheetName = "q_player")
public abstract class QPlayerMapping extends ObjectBase implements Serializable, DataKey {

    /** 主键id/等级 */
    protected int id;
    /** 升级所需要的经验值 */
    protected int exp;
    /** 属性 */
    protected wxdgaming.spring.gamebase.entity.AttrInfo attr;
    /** 属性 */
    protected wxdgaming.spring.gamebase.entity.AttrInfo attrPro;

    public Object key() {
        return id;
    }

}
