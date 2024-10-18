package push;

import org.junit.Test;
import wxdgaming.spring.boot.webclient.HttpPostJsonWork;
import wxdgaming.spring.gamebase.background.entity.bean.Account;

/**
 * 账号测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-18 14:08
 **/
public class AccountSpiTest extends TestBase {

    @Test
    public void login() {

        System.out.println(getToken());

    }

    @Test
    public void addAccount() {
        Account account = new Account();
        account.setName("pdddddddd");
        account.setPassword("123456");
        account.setNick("平多多");
        account.setEmail("pdd@localhost");
        account.setMobile("12345678901");
        account.setRoot(true);

        HttpPostJsonWork httpPostJsonWork = httpClientService.doPostJson("http://localhost:28081/account/add", account.toJson());
        httpPostJsonWork.addRequestHeader("token", getToken());
        String string = httpPostJsonWork.request().bodyString();
        System.out.println(string);
    }

}
