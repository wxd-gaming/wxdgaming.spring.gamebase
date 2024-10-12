package wxdgaming.spring.gamebase.game.server.module;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import wxdgaming.spring.boot.core.threading.ThreadContext;
import wxdgaming.spring.boot.web.BaseFilter;
import wxdgaming.spring.gamebase.game.server.bean.entity.user.Player;

/**
 * 基类
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-11 20:19
 **/
@Service
@RequestMapping("/game/**")
public class GameServerCheckSign implements BaseFilter {

    @Override public void filter(InterceptorRegistration registration) {

    }

    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // request.getCookies()[0].getValue();
        String token = request.getParameter("token");
        /*注册线程变量*/
        ThreadContext.putContent(new Player().setUid(1L));
        return true;
    }

    @Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        BaseFilter.super.postHandle(request, response, handler, modelAndView);
        /*函数调用完成后清理线程变量*/
        ThreadContext.cleanup();
    }
}
