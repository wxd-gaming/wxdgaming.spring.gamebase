package wxdgaming.spring.game.server.proto.notice;

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


/** 提示通知 */
@Getter
@Setter
@Accessors(chain = true)
public class ResNotice extends PojoBase {

    /**  */
    @Tag(1) private NoticeType noticeType;
    /**  */
    @Tag(2) private String content;

}
