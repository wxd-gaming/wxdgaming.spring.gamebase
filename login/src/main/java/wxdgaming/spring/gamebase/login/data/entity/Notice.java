package wxdgaming.spring.gamebase.login.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 公告
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-16 12:15
 **/
@Getter
@Setter
@Entity
@Table()
@Accessors(chain = true)
public class Notice {

    @Id
    @Column()
    private long uid;
    @Column()
    private long startTime;
    @Column()
    private long stopTime;
    @Column()
    private String title;
    @Column()
    private String content;

}
