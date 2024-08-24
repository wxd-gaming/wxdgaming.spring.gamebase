package wxdgaming.spring.gamebase.login.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DialectOverride;

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
    @Column(columnDefinition = "LONGTEXT COMMENT '其他数据'")
    private String other;
    @Column(columnDefinition = "LONGTEXT COMMENT '其他数据'")
    private String tmp;

}
