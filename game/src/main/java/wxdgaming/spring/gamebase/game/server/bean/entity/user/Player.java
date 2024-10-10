package wxdgaming.spring.gamebase.game.server.bean.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.core.collection.Table;
import wxdgaming.spring.boot.data.batis.EntityBase;
import wxdgaming.spring.boot.data.batis.converter.ObjectToJsonStringConverter;
import wxdgaming.spring.gamebase.game.server.bean.cache.PlayerMailCache;
import wxdgaming.spring.gamebase.game.server.bean.cache.PlayerSummaryCache;
import wxdgaming.spring.gamebase.game.server.bean.entity.mail.PlayerMail;
import wxdgaming.spring.gamebase.game.server.bean.entity.task.TaskInfo;
import wxdgaming.spring.gamebase.game.server.bean.entity.task.TaskType;

/**
 * 角色信息
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-10 19:33
 **/
@Getter
@Setter
@Accessors(chain = true)
@Entity()
public class Player extends EntityBase<Long> {

    @Convert(converter = ObjectToJsonStringConverter.class)
    @Column(columnDefinition = "LONGTEXT COMMENT '任务数据'")
    private Table<TaskType, Integer, TaskInfo> tasks = new Table<>();

    public PlayerSummary playerSummary() {
        return PlayerSummaryCache.getIns().get(this.getUid());
    }

    public PlayerMail playerMail() {
        return PlayerMailCache.getIns().get(this.getUid());
    }

}
