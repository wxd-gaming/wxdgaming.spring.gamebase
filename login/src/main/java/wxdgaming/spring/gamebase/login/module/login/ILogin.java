package wxdgaming.spring.gamebase.login.module.login;

/**
 * 登录接口
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-07 21:38
 **/
public interface ILogin {

    LoginChannel channel();

    Object login();

}
