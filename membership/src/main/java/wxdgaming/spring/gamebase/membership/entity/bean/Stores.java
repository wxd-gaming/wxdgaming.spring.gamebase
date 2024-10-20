package wxdgaming.spring.gamebase.membership.entity.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.batis.EntityAutoBase;
import wxdgaming.spring.boot.data.batis.EntityString;

/**
 * 门店
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-19 21:07
 **/
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Stores extends EntityString<Integer> {

    /** 门店名字 */
    @Column(columnDefinition = "varchar(256) comment '门店名字'")
    private String name = "";
    @Column(columnDefinition = "varchar(256) comment '门店地址'")
    private String address = "";
    /** 门店负责人 */
    @Column(columnDefinition = "varchar(11) comment '门店负责人'")
    private String leader = "店长";
    /** 门店负责人电话 */
    @Column(columnDefinition = "varchar(11) comment '门店负责人联系电话'")
    private String phoneNumber = "00000000";
    @Column(columnDefinition = "varchar(1024) comment '描述'")
    private String description = "";
    private int lv = 10;
    /** 过期时间 */
    private String expTime = "2099-01-01 00:00:00";

}
