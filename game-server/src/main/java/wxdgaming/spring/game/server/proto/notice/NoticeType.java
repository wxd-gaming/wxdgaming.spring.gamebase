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


/** 提示通知类型 */
@Getter
public enum NoticeType {

    /**  */
    @Tag(0)
    NOTICE_TYPE_NONE(0, ""),
    /**  */
    @Tag(1)
    NOTICE_TYPE_SYSTEM(1, ""),
    /**  */
    @Tag(2)
    NOTICE_TYPE_GAME(2, ""),

    ;

    private static final Map<Integer, NoticeType> static_map = MapOf.asMap(NoticeType::getCode, NoticeType.values());

    public static NoticeType valueOf(int code) {
        return static_map.get(code);
    }

    /** code */
    private final int code;
    /** 备注 */
    private final String command;

    NoticeType(int code, String command) {
        this.code = code;
        this.command = command;
    }
}
