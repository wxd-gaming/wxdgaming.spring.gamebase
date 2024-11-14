package wxdgaming.spring.gamebase.game.server.ai;


import wxdgaming.spring.boot.core.script.IScriptSingleton;
import wxdgaming.spring.gamebase.game.server.bean.MapObject;

/**
 * ai控制器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-24 00:16
 **/
public interface AiAction extends IScriptSingleton<AiType> {

    void action(MapObject object);

}
