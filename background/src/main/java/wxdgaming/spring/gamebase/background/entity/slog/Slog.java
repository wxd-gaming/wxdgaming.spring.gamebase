package wxdgaming.spring.gamebase.background.entity.slog;

import com.alibaba.fastjson.JSONObject;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.EntityBase;
import wxdgaming.spring.boot.data.converter.JSONObjectToJsonConverter;

/**
 * 日志
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-11-26 16:36
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity()
public class Slog extends EntityBase<Long> {

    int gameId;
    int sid;
    int mainSid;
    String loginAccount;
    long roleId;
    String roleName;
    int lv;
    int vipLv;
    String clientIp;
    /**
     * SELECT JSON_EXTRACT(content, '$.box') AS box_value, JSON_EXTRACT(content, '$.test') AS test_value
     * FROM logs
     * WHERE JSON_EXTRACT(content, '$.box') = 1;
     */
    @Convert(converter = JSONObjectToJsonConverter.class)
    @Column(columnDefinition = "json comment '内容'", nullable = false)
    JSONObject content;

}
