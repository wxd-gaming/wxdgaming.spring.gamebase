package wxdgaming.spring.gamebase.membership.entity.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.batis.EntityAutoBase;
import wxdgaming.spring.boot.data.batis.EntityString;

/**
 * 会员
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-19 21:03
 **/
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Member extends EntityString<Long> {

    private String name;
    private String sex;
    private int age;
    @Column(columnDefinition = "varchar(11) comment '联系电话'")
    private String phoneNumber = "";
    @Column(columnDefinition = "varchar(1024) comment '家庭地址'")
    private String address = "";
    /** 积分 */
    private int points;
    /** 当前金额 */
    private float money;
    /** 总计金额  门店 */
    private float totalMoney;
    @Column(columnDefinition = "varchar(1024) comment '描述'")
    private String description = "";

}
