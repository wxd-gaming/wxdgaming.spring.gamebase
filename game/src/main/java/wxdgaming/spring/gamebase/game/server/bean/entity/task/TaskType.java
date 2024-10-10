package wxdgaming.spring.gamebase.game.server.bean.entity.task;

import wxdgaming.spring.boot.core.collection.MapOf;
import wxdgaming.spring.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 任务类型
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-10 20:13
 **/
public enum TaskType implements IEnum {
    None(0, "默认值"),
    ;

    private static final Map<Integer, TaskType> static_map = MapOf.asMap(TaskType::getCode, TaskType.values());

    public static TaskType of(int value) {
        return static_map.get(value);
    }

    public static TaskType ofOrException(int value) {
        TaskType tmp = static_map.get(value);
        if (tmp == null) throw new RuntimeException("查找失败 " + value);
        return tmp;
    }

    private final int code;
    private final String comment;

    TaskType(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getComment() {
        return comment;
    }
}