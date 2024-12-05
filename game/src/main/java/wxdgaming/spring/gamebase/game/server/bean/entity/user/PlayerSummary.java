package wxdgaming.spring.gamebase.game.server.bean.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.EntityBase;

/**
 * 角色摘要信息
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-10 19:42
 **/
@Getter
@Setter
@Accessors(chain = true)
@Entity()
public class PlayerSummary extends EntityBase<Long> {

    @Column()
    private String userName;
    @Column()
    private String name;
    @Column()
    private long exp;
    @Column()
    private int lv;
    @Column()
    private long vipExp;
    @Column()
    private int vipLv;

    @Override public String toString() {
        return "Player{" +
               "uid=" + getUid() +
               ", name='" + name + '\'' +
               ", lv=" + lv +
               '}';
    }
}
