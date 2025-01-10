package wxdgaming.spring.game.server.proto;

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


/**
 * rpc.proto
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-10 17:03:51
 */
public class UserMessage {
   /** 执行同步等待消息 */
   @Getter
   @Setter
   @Accessors(chain = true)
   public static class ReqHeart extends PojoBase {
   
       /** 当前毫秒 */
       @Tag(1)
       private long milli;

   }

   /** 执行同步等待消息 */
   @Getter
   @Setter
   @Accessors(chain = true)
   public static class ResHeart extends PojoBase {
   
       /** 当前毫秒 */
       @Tag(1)
       private long milli;

   }

   /** null */
   @Getter
   @Setter
   @Accessors(chain = true)
   public static class ReqLogin extends PojoBase {
   
       /** jwt token */
       @Tag(2)
       private String token;
       /** 参数 json格式 */
       @Tag(3)
       private String params;

   }

   /** null */
   @Getter
   @Setter
   @Accessors(chain = true)
   public static class ResLogin extends PojoBase {
   
       /** 角色列表 */
       @Tag(2)
       private List<RoleBean> roleList = new ArrayList<>();

   }

   /** null */
   @Getter
   @Setter
   @Accessors(chain = true)
   public static class RoleBean extends PojoBase {
   
       /** 账号id */
       @Tag(1)
       private long uid;
       /** 角色id */
       @Tag(2)
       private long rid;
       /** 角色名称 */
       @Tag(3)
       private String name;
       /** 等级 */
       @Tag(4)
       private int level;
       /** 当前经验值 */
       @Tag(5)
       private long exp;

   }

}