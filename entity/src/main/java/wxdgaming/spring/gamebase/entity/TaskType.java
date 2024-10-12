package wxdgaming.spring.gamebase.entity;

import wxdgaming.spring.boot.core.collection.MapOf;
import wxdgaming.spring.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 任务类型
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-07-31 17:25
 **/
public enum TaskType implements IEnum {

    None(0, "默认值"),
    Main(1, "主线"),
    /** 日常任务 */
    Day(2, "日常"),
    Branch1(3, "支线"),
    Branch2(4, "支线"),
    Branch3(5, "支线"),
    Branch4(6, "支线"),
    Guild(7, "公会"),
    ;

    private static final Map<Integer, TaskType> static_map = MapOf.asMap(TaskType::getCode, TaskType.values());

    public static TaskType as(int value) {
        return static_map.get(value);
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