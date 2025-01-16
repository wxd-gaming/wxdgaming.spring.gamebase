package code;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import wxdgaming.spring.boot.net.message.ProtoBuf2Pojo;
import wxdgaming.spring.game.server.GameServerReflect;

@Slf4j
public class CreatePojo {


    @Test
    public void createPojo2() {
        ProtoBuf2Pojo.createMapping(
                "src/test/java",
                "code",
                "Res",
                GameServerReflect.class.getPackageName()
        );
    }


}
