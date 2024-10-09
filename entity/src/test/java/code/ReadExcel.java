package code;

import org.junit.Test;
import wxdgaming.spring.boot.data.excel.ExcelRepository;
import wxdgaming.spring.boot.data.excel.store.CreateJavaCode;
import wxdgaming.spring.gamebase.entity.a;

import java.io.File;
import java.nio.file.Paths;

public class ReadExcel {

    @Test
    public void t0() {
        ExcelRepository excelReader = new ExcelRepository();
        excelReader.readExcel(Paths.get("../cfg"), "");
        excelReader.outJsonFile("../json");
        excelReader.createCode(CreateJavaCode.getIns(), "src/main/java", a.class.getPackageName(), "");
    }

}
