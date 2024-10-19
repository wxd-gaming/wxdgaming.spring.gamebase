package wxdgaming.spring.gamebase.membership;

import java.lang.annotation.*;

/**
 * 在filter中使用，表示需要检查签名
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-18 19:45
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface CheckSign {

    boolean isRoot() default false;

    boolean isRoot2() default false;

    boolean isRoot3() default false;
}
