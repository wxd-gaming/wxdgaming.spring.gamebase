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


/** 创建角色 */
@Getter
@Setter
@Accessors(chain = true)
public class ReqCreateRole extends PojoBase {

    /** 角色名称 */
    @Tag(1) private String name;
    /** 角色职业 */
    @Tag(2) private int job;
    /** 性别 */
    @Tag(3) private int sex;

}
