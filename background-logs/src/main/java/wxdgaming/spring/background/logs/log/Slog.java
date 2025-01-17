package wxdgaming.spring.background.logs.log;

import com.alibaba.fastjson.JSONObject;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.jdbc.JsonJdbcType;
import wxdgaming.spring.boot.data.EntityBase;
import wxdgaming.spring.boot.data.converter.JSONObjectToJsonConverter;

/**
 * 日志
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-17 16:07
 **/
@Getter
@Setter
@Entity
@Table(indexes = {
        @jakarta.persistence.Index(columnList = "gameId"),
})
public class Slog extends EntityBase<Long> {

    private String logType;
    private int gameId;
    private int sid;
    private int mainSid;
    private String loginAccount;
    private long roleId;
    private String roleName;
    private int lv;
    private int vipLv;
    private String clientIp;
    /**
     * SELECT JSON_EXTRACT(content, '$.box') AS box_value, JSON_EXTRACT(content, '$.test') AS test_value
     * FROM logs
     * WHERE JSON_EXTRACT(content, '$.box') = 1;
     */
    @Convert(converter = JSONObjectToJsonConverter.class)
    @JdbcType(JsonJdbcType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json", nullable = false)
    private JSONObject content;

}
