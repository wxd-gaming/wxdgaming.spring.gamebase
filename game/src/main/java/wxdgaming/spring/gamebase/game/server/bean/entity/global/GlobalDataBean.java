package wxdgaming.spring.gamebase.game.server.bean.entity.global;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.batis.EntityBase;

/**
 * 全局数据
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-11-21 20:58
 **/
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(
        indexes = {
                @Index(columnList = "sid"),
                @Index(columnList = "typeId"),
                @Index(columnList = "mergeTime"),

        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"sid", "typeId"})
        }
)
public class GlobalDataBean extends EntityBase<Integer> {

    @Column(nullable = false)
    private int sid;
    @Column(nullable = false)
    private int typeId;
    @Column(nullable = false)
    private long mergeTime;
    private byte[] data;

}
