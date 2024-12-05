package wxdgaming.spring.gamebase.game.server.bean.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.core.collection.Table;
import wxdgaming.spring.boot.data.converter.ObjectToJsonStringConverter;
import wxdgaming.spring.boot.net.SocketSession;
import wxdgaming.spring.gamebase.entity.TaskType;
import wxdgaming.spring.gamebase.game.server.bean.MapObject;
import wxdgaming.spring.gamebase.game.server.bean.cache.PlayerMailCache;
import wxdgaming.spring.gamebase.game.server.bean.cache.PlayerSummaryCache;
import wxdgaming.spring.gamebase.game.server.bean.entity.mail.PlayerMail;
import wxdgaming.spring.gamebase.game.server.bean.entity.task.TaskInfo;

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
public class Player extends MapObject {

    @Convert(converter = ObjectToJsonStringConverter.class)
    @Column(columnDefinition = "LONGTEXT COMMENT '任务数据'")
    private Table<TaskType, Integer, TaskInfo> tasks = new Table<>();

    private transient SocketSession session;

    public Player() {
        super(ObjectType.Player);
    }

    public PlayerSummary playerSummary() {
        return PlayerSummaryCache.getIns().getIfPresent(this.getUid());
    }

    public PlayerMail playerMail() {
        return PlayerMailCache.getIns().getIfPresent(this.getUid());
    }

    public void writeAndFlush(String jsonData) {
        session.writeAndFlush(jsonData);
    }

    @Override public String toString() {
        return String.valueOf(playerSummary());
    }
}
