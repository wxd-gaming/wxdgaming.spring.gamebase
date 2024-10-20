package code;

import org.junit.Test;
import wxdgaming.spring.boot.core.util.StringsUtil;
import wxdgaming.spring.boot.webclient.HttpPostJsonWork;
import wxdgaming.spring.gamebase.membership.entity.bean.Account;

/**
 * 处理测试数据
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-19 22:01
 **/
public class PushTest extends BaseTest {

    @Test
    public void t0() {
        System.out.println(StringsUtil.randomUuid16("", 32));
    }

    @Test
    public void login() {

        System.out.println(getToken());

    }

    @Test
    public void pushStores() {
        pushStores("1号店");
        pushStores("2号店");
        pushStores("3号店");
    }

    public void pushStores(String name) {
        String string = httpClientService.doPostJson(
                        String.format("http://localhost:%s/stores/push", port),
                        """
                                { "name": "%s", "address": "%s", "leader": "%s", "phoneNumber": "%s", "description": "%s", "lv": %s, "expTime": "%s" }"""
                                .formatted(name, "方亭街", "曾小妹儿", "00000000", name, 1, "2026-01-01 00:00:00")
                )
                .addRequestHeader("token", getToken())
                .request()
                .bodyString();
        System.out.println(string);
    }

    @Test
    public void addAccount1() {
        Account account = new Account();
        account.setName("什邡一号店");
        account.setPassword("123456");
        account.setNick("平多多");
        account.setEmail("pdd@localhost");
        account.setMobile("12345678901");
        account.setRoot2(true);

        HttpPostJsonWork httpPostJsonWork = httpClientService.doPostJson("http://localhost:8081/account/add", account.toJson());
        httpPostJsonWork.addRequestHeader("token", getToken());
        String string = httpPostJsonWork.request().bodyString();
        System.out.println(string);
    }

    @Test
    public void addAccount2() {
        Account account = new Account();
        account.setName("什邡一号店");
        account.setPassword("123456");
        account.setNick("平多多");
        account.setEmail("pdd@localhost");
        account.setMobile("12345678901");
        account.setRoot2(true);

        HttpPostJsonWork httpPostJsonWork = httpClientService.doPostJson("http://localhost:8081/account/add", account.toJson());
        httpPostJsonWork.addRequestHeader("token", getToken());
        String string = httpPostJsonWork.request().bodyString();
        System.out.println(string);
    }

}