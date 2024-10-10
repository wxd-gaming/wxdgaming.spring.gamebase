import com.alibaba.fastjson.TypeReference;
import org.junit.Test;
import wxdgaming.spring.boot.core.collection.Table;
import wxdgaming.spring.boot.core.json.FastJsonUtil;
import wxdgaming.spring.gamebase.game.server.bean.entity.task.TaskInfo;
import wxdgaming.spring.gamebase.game.server.bean.entity.task.TaskType;

public class JsonTest {

    @Test
    public void t10() {

        Table<TaskType, Integer, TaskInfo> tasks = new Table<>();
        tasks.put(TaskType.None, 1, new TaskInfo());

        String jsonWriteType = tasks.toJsonWriteType();
        System.out.println(jsonWriteType);
        Object parse = FastJsonUtil.parse(jsonWriteType, new TypeReference<>() {});
        System.out.println(parse);
    }

}
