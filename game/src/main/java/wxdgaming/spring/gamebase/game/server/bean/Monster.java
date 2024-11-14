package wxdgaming.spring.gamebase.game.server.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.lang.LNum;
import wxdgaming.spring.boot.data.excel.store.DataRepository;
import wxdgaming.spring.gamebase.entity.cfg.QMonsterTable;
import wxdgaming.spring.gamebase.entity.cfg.bean.QMonster;

/**
 * 怪物
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-17 11:25
 **/
@Getter
@Setter
@Accessors(chain = true)
public class Monster extends MapObject {

    public enum MonsterType {
        普通,
        精英,
        BOSS,
    }

    /** 名字 */
    private String name;
    /** 等级 */
    private int lv;
    /** 经验值 */
    private LNum exp = new LNum();

    public Monster() {
        super(ObjectType.Monster);
    }

    public QMonster qMonster() {
        return SpringUtil.getIns().getBean(DataRepository.class).dataTable(QMonsterTable.class).get(getCfgId());
    }

    @Override public Monster setUid(Long uid) {
        super.setUid(uid);
        return this;
    }

    @Override public Monster setCfgId(int cfgId) {
        super.setCfgId(cfgId);
        return this;
    }

    @Override public String toString() {
        return "Monster{" +
               "uid=" + getUid() +
               ", cfg=" + getCfgId() +
               ", name=" + qMonster().getName() +
               ", lv=" + qMonster().getLv() +
               '}';
    }
}
