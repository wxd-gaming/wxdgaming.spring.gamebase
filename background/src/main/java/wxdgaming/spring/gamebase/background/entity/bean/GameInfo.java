package wxdgaming.spring.gamebase.background.entity.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.batis.EntityBase;

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
public class GameInfo extends EntityBase<Integer> {

    @Column()
    private String name;
    @Column()
    private String icon;
    @Column()
    private String description;
    @Column()
    private long updateTime;
}
