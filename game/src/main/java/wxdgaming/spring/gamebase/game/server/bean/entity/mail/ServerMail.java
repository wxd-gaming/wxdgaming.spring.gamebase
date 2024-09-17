package wxdgaming.spring.gamebase.game.server.bean.entity.mail;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table
@Entity
public class ServerMail extends RoleMail {

    @Column(columnDefinition = "int COMMENT '内容参数'")
    private int minVipLv;
    @Column(columnDefinition = "int COMMENT '内容参数'")
    private int maxVipLv;

    @Column(columnDefinition = "int COMMENT '内容参数'")
    private int minLv;
    @Column(columnDefinition = "int COMMENT '内容参数'")
    private int maxLv;

    @Convert(converter = LongListToJsonConverter.class)
    @Column(columnDefinition = "longtext COMMENT '指定能领取的人员'")
    private List<Long> targetRids = new ArrayList<>();
    @Convert(converter = LongListToJsonConverter.class)
    @Column(columnDefinition = "longtext COMMENT '已经领取奖励的人员'")
    private List<Long> rewardRids = new ArrayList<>();
}
