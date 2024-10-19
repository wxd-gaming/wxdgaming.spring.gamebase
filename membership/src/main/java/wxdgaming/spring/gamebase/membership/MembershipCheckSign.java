package wxdgaming.spring.gamebase.membership;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.core.threading.ThreadContext;
import wxdgaming.spring.boot.core.util.JwtUtils;
import wxdgaming.spring.boot.core.util.StringsUtil;
import wxdgaming.spring.boot.web.BaseFilter;
import wxdgaming.spring.boot.web.service.ResponseService;
import wxdgaming.spring.gamebase.membership.entity.bean.Account;
import wxdgaming.spring.gamebase.membership.entity.store.AccountRepository;

/**
 * 基类
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-11 20:19
 **/
@Service
@RequestMapping("/**")
public class MembershipCheckSign implements BaseFilter {

    @Autowired AccountRepository accountRepository;
    @Autowired ResponseService responseService;

    @Override public void filter(InterceptorRegistration registration) {

    }

    boolean sendMessage(HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        CheckSign classCheckSign = ((HandlerMethod) handler).getBean().getClass().getAnnotation(CheckSign.class);
        CheckSign methodCheckSign = handlerMethod.getMethod().getAnnotation(CheckSign.class);
        if (classCheckSign != null || methodCheckSign != null) {
            sendMessage(response, "验签失败");
            return false;
        }
        return true;
    }

    void sendMessage(HttpServletResponse response, String msg) throws Exception {
        responseService.response(response, ContentType.APPLICATION_JSON.toString(), RunResult.error(msg).toJSONString());
    }

    boolean checkSign(HttpServletResponse response, CheckSign checkSign, Account account) throws Exception {
        if (checkSign.isRoot() && !account.isRoot()) {
            sendMessage(response, "权限不足");
            return false;
        } else if (checkSign.isRoot2() && !account.isRoot2()) {
            sendMessage(response, "权限不足");
            return false;
        } else if (checkSign.isRoot3() && !account.isRoot3()) {
            sendMessage(response, "权限不足");
            return false;
        }
        return true;
    }

    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod handlerMethod)) return true;
        String token = request.getHeader("token");
        if (StringsUtil.notEmptyOrNull(token)) {
            try {
                Jws<Claims> claimsJws = JwtUtils.parseJWT(token);
                String string = claimsJws.getPayload().get("accountName", String.class);
                if (StringsUtil.emptyOrNull(string)) {
                    return sendMessage(response, handler);
                }
                Account account = accountRepository.findByName(string);
                if (account == null) {
                    return sendMessage(response, handler);
                }
                CheckSign methodCheckSign = handlerMethod.getMethod().getAnnotation(CheckSign.class);
                if (methodCheckSign != null && !checkSign(response, methodCheckSign, account))
                    return false;

                CheckSign classCheckSign = handlerMethod.getClass().getAnnotation(CheckSign.class);
                if (classCheckSign != null && !checkSign(response, classCheckSign, account))
                    return false;
                /*注册线程变量*/
                ThreadContext.putContent(account);
            } catch (Exception e) {
                return sendMessage(response, handler);
            }
        } else {
            return sendMessage(response, handler);
        }
        return true;
    }

    @Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        BaseFilter.super.postHandle(request, response, handler, modelAndView);
        /*函数调用完成后清理线程变量*/
        ThreadContext.cleanup();
    }

}
