package wxdgaming.spring.game.server.proto.user;

import io.protostuff.Tag;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.core.collection.MapOf;
import wxdgaming.spring.boot.net.message.PojoBase;


/** 玩家心跳包 */
@Getter
@Setter
@Accessors(chain = true)
public class ResHeart extends PojoBase {

    /** 当前毫秒 */
    @Tag(1) private long milli;

}
