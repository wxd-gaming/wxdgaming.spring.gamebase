package wxdgaming.spring.gamebase.login.data.entity;

import com.alibaba.fastjson.JSONObject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DialectOverride;
import wxdgaming.spring.boot.data.batis.converter.JSONObjectToJsonConverter;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-24 16:17
 **/
@Getter
@Setter
@Entity
@Table
@Accessors(chain = true)
public class User {

    @Id
    @Column(length = 64)
    private String openId;
    @Column(length = 64)
    private String account;
    @Column(length = 64)
    private String token;
    @Convert(converter = JSONObjectToJsonConverter.class)
    @Column(columnDefinition = "LONGTEXT COMMENT '其他数据'")
    private JSONObject other;
    @Column(columnDefinition = "LONGTEXT COMMENT '其他数据'")
    private String tmp;

}
