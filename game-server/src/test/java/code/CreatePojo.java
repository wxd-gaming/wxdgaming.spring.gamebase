package code;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import wxdgaming.spring.boot.net.message.ProtoBuf2Pojo;
import wxdgaming.spring.game.server.GameServerReflect;

@Slf4j
public class CreatePojo {

    @Test
    public void createPojo() {
        ProtoBuf2Pojo.actionProtoFile("./src/main/java", "./src/proto");
    }

    @Test
    public void createPojo2() {
        ProtoBuf2Pojo.createMapping(
                "../game-server-script/src/main/java",
                "wxdgaming.spring.game.script",
                "Req",
                GameServerReflect.class.getPackageName()
        );
    }


}
