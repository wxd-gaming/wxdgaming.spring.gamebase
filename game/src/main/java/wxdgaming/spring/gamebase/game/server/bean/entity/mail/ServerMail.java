package wxdgaming.spring.gamebase.game.server.bean.entity.mail;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.batis.converter.LongListToJsonConverter;

import java.util.ArrayList;
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
@Entity
public class ServerMail extends MailBase {

    @Column(columnDefinition = "int COMMENT '领取邮件的vip等级限制'")
    private int minVipLv;
    @Column(columnDefinition = "int COMMENT '领取邮件的vip等级限制'")
    private int maxVipLv;

    @Column(columnDefinition = "int COMMENT '领取邮件的等级限制'")
    private int minLv;
    @Column(columnDefinition = "int COMMENT '领取邮件的等级限制'")
    private int maxLv;

    @Convert(converter = LongListToJsonConverter.class)
    @Column(columnDefinition = "longtext COMMENT '指定玩家才可以领取'")
    private List<Long> targetRids = new ArrayList<>();
}
