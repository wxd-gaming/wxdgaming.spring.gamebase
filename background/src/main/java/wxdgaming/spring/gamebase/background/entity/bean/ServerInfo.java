package wxdgaming.spring.gamebase.background.entity.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.EntityBase;

/**
 * 服务器区服信息
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-14 13:46
 **/
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = {
        @jakarta.persistence.Index(columnList = "gameId"),
        @jakarta.persistence.Index(columnList = "platform"),
        @jakarta.persistence.Index(columnList = "sid"),
})
public class ServerInfo extends EntityBase<Long> {

    /** 游戏id */
    @Column(columnDefinition = "int COMMENT '游戏id'")
    private int gameId;
    /** 平台 */
    @Column(columnDefinition = "varchar(64) COMMENT '服务器名字'")
    private String platform;
    /** 服务器id */
    @Column(columnDefinition = "int COMMENT '服务器id'")
    private int sid;
    /** 服务器id */
    @Column(columnDefinition = "int COMMENT '主服的服务器id'")
    private int mainSid;
    /** 服务器名字 */
    @Column(columnDefinition = "varchar(128) COMMENT '服务器名字'")
    private String name;
    /** 服务器显示名 */
    @Column(columnDefinition = "varchar(128) COMMENT '服务器显示名字'")
    private String showName;
    /** 开服时间 */
    @Column(columnDefinition = "varchar(64) COMMENT '开服时间'")
    private String openTime;
    /** 维护时间 */
    @Column(columnDefinition = "varchar(64) COMMENT '维护时间'")
    private String maintainTime;
    /** ip */
    @Column(columnDefinition = "varchar(64) COMMENT '外网ip'")
    private String wlan;
    /** ip */
    @Column(columnDefinition = "varchar(64) COMMENT '内网ip'")
    private String lan;
    /** 端口 */
    @Column()
    private int port;
    /** web 端口 */
    @Column()
    private int webPort;
    /** 状态 */
    @Column()
    private String status;
    /** 版本 */
    @Column(columnDefinition = "varchar(128) COMMENT '服务器版本号'")
    private String version;
    /** 注册账户 */
    @Column()
    private int registerUserCount;
    /** 注册角色 */
    @Column()
    private int registerRoleCount;
    /** 在线角色 */
    @Column()
    private int onlineRoleCount;
    /** 活跃用户 */
    @Column(columnDefinition = "int DEFAULT 0 COMMENT '活跃用户数'")
    private int activeRoleCount;
    /** 充值金额 */
    @Column()
    private long rechargeCount;
    /** 最后更新时间 */
    @Column()
    private long updateTime;

}
