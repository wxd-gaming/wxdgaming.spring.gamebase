package wxdgaming.spring.game.server.bean.entity.global;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import wxdgaming.spring.boot.data.EntityBase;
import wxdgaming.spring.boot.data.converter.ObjectToJsonStringConverter;

/**
 * 全局数据
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2025-01-10 16:33
 **/
@Getter
@Setter
@Entity
public class GlobalData extends EntityBase<Long> {

    private int sid;
    private GlobalType type;
    @Convert(converter = ObjectToJsonStringConverter.class)
    @Column(columnDefinition = "text")
    private GlobalDataBase data;

    @Override public Long getUid() {
        return sid * 1000L + type.getCode();
    }

    @Override public GlobalData setUid(Long uid) {
        super.setUid(uid);
        return this;
    }
}
