package wxdgaming.spring.gamebase.background.entity.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.batis.EntityAutoBase;

/**
 * 游戏信息
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-14 14:18
 **/
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = {})
public class GameInfo extends EntityAutoBase<Integer> {

    @Column(columnDefinition = "varchar(32) comment '游戏名字'", nullable = false)
    private String name;
    @Column()
    private String icon;
    @Column()
    private String description;
    @Column()
    private long updateTime;
    /** 上报数据用的key */
    @Column(columnDefinition = "varchar(32) comment 'appkey'", nullable = false)
    private String appKey;
    /** 充值 */
    @Column(columnDefinition = "varchar(32) comment 'rechargeKey'", nullable = false)
    private String rechargeKey;
}
