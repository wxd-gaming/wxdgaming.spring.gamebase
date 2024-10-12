package code;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.junit.Test;
import wxdgaming.spring.boot.core.json.FastJsonUtil;
import wxdgaming.spring.boot.core.lang.ObjectBase;
import wxdgaming.spring.boot.data.excel.ExcelRepository;
import wxdgaming.spring.boot.data.excel.store.CreateJavaCode;
import wxdgaming.spring.boot.data.excel.store.DataRepository;
import wxdgaming.spring.gamebase.entity.TaskType;
import wxdgaming.spring.gamebase.entity.cfg.a;

import java.nio.file.Paths;

public class ReadExcel {

    @Test
    public void t0() {
        ExcelRepository excelReader = new ExcelRepository();
        excelReader.readExcel(Paths.get("../cfg"), "");
        excelReader.outJsonFile("../cfg-json");
        excelReader.createCode(CreateJavaCode.getIns(), "src/main/java", a.class.getPackageName(), "");
    }


    @Test
    public void loadExcelCode() {
        DataRepository dataRepository = new DataRepository()
                .setClassLoader(this.getClass().getClassLoader())
                .setScanPackageName(a.class.getPackageName())
                .setJsonPath("../cfg-json");
        dataRepository.load();
    }

    @Test
    public void t11() {
        String json = "\"Main\"";
        String json1 = FastJsonUtil.toJson(new TaskInfo());
        System.out.println(json1);
        System.out.println(FastJsonUtil.parse(json1, TaskInfo.class));
        System.out.println(FastJsonUtil.parse(json, TaskType.class));
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class TaskInfo extends ObjectBase {

        private TaskType type = TaskType.Main;
        private int count = 1;
        private int completeCount = 1;

    }
}
