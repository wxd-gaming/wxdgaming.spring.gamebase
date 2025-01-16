package wxdgaming.spring.game.server.bean.entity.user;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import wxdgaming.spring.boot.data.EntityBase;
import wxdgaming.spring.game.server.bean.entity.mail.Mail;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 邮件背包
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-10 16:32
 **/
@Getter
@Setter
@Entity
public class MailPack extends EntityBase<Long> {

    private final ConcurrentHashMap<Long, Mail> packs = new ConcurrentHashMap<>();

}
