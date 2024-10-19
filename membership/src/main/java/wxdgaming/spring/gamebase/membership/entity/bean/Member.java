package wxdgaming.spring.gamebase.membership.entity.bean;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.batis.EntityAutoBase;

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
public class Member extends EntityAutoBase<Long> {

    private String name;
    private String sex;
    private int age;
    private String phoneNumber;
    private String address;
    /** 当前金额 */
    private float money;
    /** 总计金额  门店 */
    private float totalMoney;

}
