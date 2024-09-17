package wxdgaming.spring.gamebase.game.server.bean.entity.mail;

import jakarta.persistence.*;
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
@Entity
public class RoleMail extends MailBase {


}
