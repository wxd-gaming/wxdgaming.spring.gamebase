package wxdgaming.spring.gamebase.membership.entity.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.batis.EntityBase;
import wxdgaming.spring.boot.data.batis.EntityString;
import wxdgaming.spring.boot.data.batis.converter.ObjectToJsonStringConverter;

import java.util.HashSet;

/**
 * 账号
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-18 11:05
 **/
@Getter
@Setter
@Accessors(chain = true)
@Entity()
@Table(indexes =
        {@Index(columnList = "forAccountId"), @Index(columnList = "name"), @Index(columnList = "name2")},
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "name2"})
)
public class Account extends EntityString<Integer> {

    private int forAccountId = 0;
    /** 账号 */
    @Column(columnDefinition = "varchar(64) comment '账号'", nullable = false)
    private String name;
    /** 账号 */
    @Column(columnDefinition = "varchar(64) comment '子账号'", nullable = false)
    private String name2;
    /** 用于页面显示的名字 */
    @Column(columnDefinition = "varchar(64) comment '显示的名称'", nullable = false)
    private String nick;
    /** 密钥 */
    @Column(columnDefinition = "varchar(64) comment '密钥'", nullable = false)
    private String password;
    /** 邮箱地址 */
    @Column(columnDefinition = "varchar(64) comment '邮箱地址'", nullable = false)
    private String email;
    /** 手机 */
    @Column(columnDefinition = "varchar(64) comment '手机号码'", nullable = false)
    private String mobile;
    /** 是否root账号 */
    @Column(columnDefinition = "varchar(64) comment '超级账号'", nullable = false)
    private boolean root;
    /** 是否root账号 */
    @Column(columnDefinition = "varchar(64) comment '超级账号'", nullable = false)
    private boolean root2;
    /** 是否root账号 */
    @Column(columnDefinition = "varchar(64) comment '超级账号'", nullable = false)
    private boolean root3;
    @Column(columnDefinition = "varchar(1024) comment '描述'")
    private String description = "";

    /** 账号能查看的游戏列表，授权 */
    @Convert(converter = ObjectToJsonStringConverter.class)
    @Column(columnDefinition = "longtext comment '账号授权查看的门店列表'", nullable = false)
    private HashSet<Integer> storesList = new HashSet<>();

}
