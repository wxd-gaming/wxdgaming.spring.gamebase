package wxdgaming.spring.game.server.bean.entity.mail;

import lombok.Getter;
import lombok.Setter;
import wxdgaming.spring.boot.core.lang.ObjectBase;
import wxdgaming.spring.game.server.bean.entity.bag.Item;

import java.util.List;

/**
 * 邮件
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-15 11:28
 **/
@Getter
@Setter
public class Mail extends ObjectBase {

    private long uid;
    private long createdTime;
    private boolean ready = false;
    private boolean reward = false;
    private long expireTime;
    private int mailId = 0;
    private String title;
    private String content;
    private List<Item> items;

}
