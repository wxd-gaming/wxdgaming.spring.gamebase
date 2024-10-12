package wxdgaming.spring.gamebase.game.server.bean.entity.task;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.core.lang.ObjectBase;
import wxdgaming.spring.gamebase.entity.TaskType;

/**
 * 任务详情
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-10 20:15
 **/
@Getter
@Setter
@Accessors(chain = true)
public class TaskInfo extends ObjectBase {

    private TaskType type = TaskType.Main;
    private int count = 1;
    private int completeCount = 1;

}
