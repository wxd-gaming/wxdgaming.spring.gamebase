package wxdgaming.spring.gamebase.game.server.bean.entity.global;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.core.json.FastJsonUtil;
import wxdgaming.spring.boot.core.lang.ObjectBase;

import java.io.Serial;
import java.io.Serializable;

/**
 * 日志基础
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-11-22 14:38
 **/
@Getter
@Setter
@Accessors(chain = true)
public abstract class GlobalBase extends ObjectBase implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private int sid;
    @Column(nullable = false)
    private int typeId;
    @Column(nullable = false)
    private long mergeTime;

    public GlobalDataBean globalDataBean() {
        GlobalDataBean globalDataBean = new GlobalDataBean();
        globalDataBean.setUid(sid * 1000 + typeId);
        globalDataBean
                .setSid(sid)
                .setTypeId(typeId)
                .setMergeTime(mergeTime)
                .setData(FastJsonUtil.toBytesWriteType(this));
        return globalDataBean;
    }

}
