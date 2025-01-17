package wxdgaming.spring.background.logs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.EntityBase;

/**
 * 游戏信息
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-17 16:07
 **/
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = {
        @jakarta.persistence.Index(columnList = "gameId", unique = true),
        @jakarta.persistence.Index(columnList = "gameName"),
})
public class GameInfo extends EntityBase<Integer> {

    private int gameId;
    private String gameName;
    private String gameVersion;
    private String gameComment;


}
