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


/** 回复登录消息 */
@Getter
@Setter
@Accessors(chain = true)
public class ResLogin extends PojoBase {

    /** 角色列表 */
    @Tag(2) private List<RoleBean> roleList = new ArrayList<>();

}
