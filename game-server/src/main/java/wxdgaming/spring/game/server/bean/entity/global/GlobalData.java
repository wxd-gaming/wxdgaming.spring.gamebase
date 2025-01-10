package wxdgaming.spring.game.server.bean.entity.global;

import wxdgaming.spring.boot.data.EntityBase;

/**
 * 全局数据
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-10 16:33
 **/
public class GlobalData extends EntityBase<Long> {

    private int sid;
    private int type;
    private GlobalDataBase data;

    @Override public Long getUid() {
        return sid * 1000L + type;
    }
}