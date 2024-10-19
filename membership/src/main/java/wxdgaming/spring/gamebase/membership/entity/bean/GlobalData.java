package wxdgaming.spring.gamebase.membership.entity.bean;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.core.format.HexId;
import wxdgaming.spring.boot.data.batis.EntityBase;

/**
 * 全局数据
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-18 13:12
 **/
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class GlobalData extends EntityBase<Integer> {

    private transient HexId accountId = new HexId(1);
    private transient HexId serverId = new HexId(1);

}
