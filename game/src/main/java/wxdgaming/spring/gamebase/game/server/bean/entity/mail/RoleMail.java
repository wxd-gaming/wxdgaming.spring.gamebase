package wxdgaming.spring.gamebase.game.server.bean.entity.mail;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.batis.EntityBase;
import wxdgaming.spring.boot.data.batis.converter.ObjectListToJsonConverter;

import java.util.List;

/**
 * 角色邮件
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-16 22:59
 **/
@Getter
@Setter
@Accessors(chain = true)
@Table
@Entity
public class RoleMail extends EntityBase<Long> {

    /** 发送者 */
    @Column(columnDefinition = "bigint NOT NULL DEFAULT 0 COMMENT '发送者'")
    private long sender;
    /** 发送者 */
    @Column(columnDefinition = "varchar(64) COMMENT '发送者'")
    private String senderName;
    /** 标题 */
    @Column(columnDefinition = "varchar(256) COMMENT '标题'")
    private String title;
    /** 内容 */
    @Column(columnDefinition = "varchar(2048) COMMENT '内容'")
    private String content;
    @Convert(converter = ObjectListToJsonConverter.class)
    @Column(columnDefinition = "varchar(2048) COMMENT '内容参数'")
    private List<Object> contentParams;
    /** 过期时间 */
    @Column(columnDefinition = "bigint NOT NULL DEFAULT 0 COMMENT '过期时间'")
    private long expirationTime;

}
