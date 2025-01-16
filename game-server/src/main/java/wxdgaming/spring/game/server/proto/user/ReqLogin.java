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


/** 登录请求 */
@Getter
@Setter
@Accessors(chain = true)
public class ReqLogin extends PojoBase {

    /** jwt token */
    @Tag(2) private String token;
    /** 参数 json格式 */
    @Tag(3) private String params;

}
