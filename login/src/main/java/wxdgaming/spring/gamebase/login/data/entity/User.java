package wxdgaming.spring.gamebase.login.data.entity;

import com.alibaba.fastjson.JSONObject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.batis.converter.JSONObjectToJsonConverter;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-24 16:17
 **/
@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(columnList = "account", unique = false)
})
@Accessors(chain = true)
public class User {

    @Id
    @Column(columnDefinition = "varchar(64) COMMENT '唯一id'")
    private String openId;
    @Column(columnDefinition = "varchar(64) COMMENT '渠道'", nullable = false)
    private String channel;
    @Column(columnDefinition = "varchar(64) COMMENT '账号'", nullable = false)
    private String account;
    @Column(columnDefinition = "varchar(64) COMMENT '密钥'", nullable = false)
    private String token;
    @Column(columnDefinition = "bigint NOT NULL DEFAULT 0 COMMENT '创建时间'")
    private long createTime;/*这个账号创建时间不已这个字段为准而是，通过es等系统的登录日志记录*/
    @Column(columnDefinition = "bigint NOT NULL DEFAULT 0 COMMENT '被触摸的时间'")
    private long touchTime;
    @Column(columnDefinition = "bigint NOT NULL DEFAULT 0 COMMENT '登录成功时间'")
    private long loginTime;
    @Column(columnDefinition = "bigint NOT NULL DEFAULT 0 COMMENT '登录次数'")
    private long loginCount;
    @Convert(converter = JSONObjectToJsonConverter.class)
    @Column(columnDefinition = "LONGTEXT COMMENT '其他数据'")
    private JSONObject other;

    @Override public String toString() {
        return "User{" +
                "openId='" + openId + '\'' +
                ", channel='" + channel + '\'' +
                ", account='" + account + '\'' +
                '}';
    }

}
