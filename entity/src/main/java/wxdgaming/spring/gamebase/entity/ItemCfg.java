package wxdgaming.spring.gamebase.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import wxdgaming.spring.boot.core.lang.ObjectBase;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-11-07 20:55
 **/
@Getter
@AllArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true)
public class ItemCfg extends ObjectBase {

    @JSONField(ordinal = 1)
    protected long uid;
    @JSONField(ordinal = 2)
    protected int cfg;
    @JSONField(ordinal = 3)
    protected long num;
    @JSONField(ordinal = 4)
    protected boolean bind;
    /** 持续时间 毫秒 */
    @JSONField(ordinal = 5)
    protected long expire;

    // public ItemType itemType() {
    //     return qItemBean().getItemType();
    // }
    //
    // public QItemBean qItemBean() {
    //     return CfgCache.getIns().getDbBean(QItemTable.class, cfg);
    // }

    public long changeNum(long change) {
        this.num = Math.max(0, Math.addExact(num, change));
        return this.num;
    }

}
