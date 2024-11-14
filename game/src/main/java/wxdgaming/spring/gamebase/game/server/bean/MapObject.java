package wxdgaming.spring.gamebase.game.server.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.core.SpringUtil;
import wxdgaming.spring.boot.core.timer.MyClock;
import wxdgaming.spring.gamebase.entity.AttrInfo;
import wxdgaming.spring.gamebase.entity.AttrType;
import wxdgaming.spring.gamebase.entity.Const;
import wxdgaming.spring.gamebase.entity.ModuleGroup;
import wxdgaming.spring.gamebase.game.server.ai.AiType;
import wxdgaming.spring.gamebase.game.server.module.datacache.DataCenter;

import java.util.HashMap;

/**
 * 场景对象
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-17 11:19
 **/
@Getter
@Setter
@Accessors(chain = true)
public class MapObject extends GameBase {

    @Getter
    public enum ObjectType {
        Player("玩家"),
        Monster("怪物");

        private final String comment;

        ObjectType(String comment) {
            this.comment = comment;
        }
    }

    /** 对象类型 */
    final ObjectType objectType;


    private long mapId;
    private int mapCfgId;

    /** 基础的数值属性，下线就没有了 */
    private transient HashMap<ModuleGroup, AttrInfo> groupAttrMap = new HashMap<>();
    /** 翻倍属性，下线就没有了 */
    private transient HashMap<ModuleGroup, AttrInfo> groupAttrProMap = new HashMap<>();
    /** 临时属性，下线就没有了 */
    private transient AttrInfo tempAttrMap = new AttrInfo();
    /** 附加属性-会存入数据库的 */
    private AttrInfo addAttrMap = new AttrInfo();
    /** 最终属性-会存入数据库的 */
    private AttrInfo finalAttrMap = new AttrInfo();
    /** 死亡时间 */
    private long dieTime;
    /** 当前血量 */
    private long hp;
    /** 战斗力 */
    private long power;
    /** 最大战斗力 */
    private long maxPower;
    /** ai类型 */
    private transient AiType aiType = AiType.IDLE;
    /** 切换ai的间隔时间 */
    private transient long changeAiTime;
    /** 当前地图 */
    private transient MapInfo mapInfo;
    /** 攻击目标对象 */
    private transient MapObject targetMapObject;

    public MapObject(ObjectType objectType) {
        this.objectType = objectType;
    }

    private String queueName = null;

    public String getQueueName() {
        if (queueName == null) {
            if (getUid() > 0) {
                DataCenter dataCenter = SpringUtil.getIns().getBean(DataCenter.class);
                int i = (int) (getUid() % dataCenter.getLogicCoreSize());
                queueName = Const.MapQueue + i;
            }
        }
        return queueName;
    }

    /** 丢到玩家线程 */
    public void addEvent(Runnable event) {
        DataCenter dataCenter = SpringUtil.getIns().getBean(DataCenter.class);
        dataCenter.pushEvent(getQueueName(), event);
    }

    /** 丢到玩家所在的地图执行 */
    public void addMapEvent(Runnable event) {
        getMapInfo().addEvent(event);
    }

    /**
     * 唯一ID
     *
     * @param uid
     */
    @Override public MapObject setUid(Long uid) {
        super.setUid(uid);
        return this;
    }

    /**
     * 配置ID
     *
     * @param cfgId
     */
    @Override public MapObject setCfgId(int cfgId) {
        super.setCfgId(cfgId);
        return this;
    }

    public long maxHp() {
        return getFinalAttrMap().get(AttrType.MAXHP);
    }

    public long changeHp(long change) {
        hp = Math.min(
                maxHp(),
                Math.max(0, Math.addExact(hp, change))
        );
        if (isDie()) {
            dieTime = MyClock.millis();
        } else {
            dieTime = 0;
        }
        return hp;
    }

    /**
     * 尝试切换ai
     *
     * @param aiType
     * @param cd
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-03-24 00:52
     */
    public boolean tryChangeAi(AiType aiType, long cd) {
        if (MyClock.millis() > this.changeAiTime) {
            changeAi(aiType, cd);
            return true;
        }
        return false;
    }

    /***
     * 强壮切换ai
     * @param aiType
     * @param cd
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-03-24 00:43
     */
    public void changeAi(AiType aiType, long cd) {
        this.aiType = aiType;
        this.changeAiTime = MyClock.millis() + cd;
    }

    @JSONField(deserialize = false)
    public boolean isDie() {
        return this.getHp() < 1;
    }

    @Override public String toString() {
        return this.getClass().getSimpleName() + "【" + objectType.getComment() + "】"
               + "{" + "uid=" + this.getUid() + "}";
    }
}
