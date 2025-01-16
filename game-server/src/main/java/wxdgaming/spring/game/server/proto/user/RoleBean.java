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


/** 角色信息 */
@Getter
@Setter
@Accessors(chain = true)
public class RoleBean extends PojoBase {

    /** 账号id */
    @Tag(1) private long uid;
    /** 角色id */
    @Tag(2) private long rid;
    /** 角色名称 */
    @Tag(3) private String name;
    /** 等级 */
    @Tag(4) private int level;
    /** 当前经验值 */
    @Tag(5) private long exp;

}
