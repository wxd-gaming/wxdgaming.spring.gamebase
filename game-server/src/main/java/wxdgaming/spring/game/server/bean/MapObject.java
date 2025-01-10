package wxdgaming.spring.game.server.bean;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.spring.boot.data.EntityBase;
import wxdgaming.spring.boot.data.converter.ObjectToJsonStringConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * 场景对象
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-09 20:46
 **/
@Getter
@Setter
@Accessors(chain = true)
@MappedSuperclass
public class MapObject extends EntityBase<Long> {

    private String name;
    private int cfgId;
    private int lv;
    private long exp;
    private long hp;
    private long mp;

    private transient Map<AttributeModule, AttributeInfo> attributeModuleMap = new HashMap<>();
    @Convert(converter = ObjectToJsonStringConverter.class)
    private AttributeInfo attributeFinal = new AttributeInfo();


}
