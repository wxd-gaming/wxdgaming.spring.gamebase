package push;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wxdgaming.spring.boot.core.CoreScan;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.core.threading.VirtualExecutor;
import wxdgaming.spring.boot.webclient.HttpClientBuild;
import wxdgaming.spring.boot.webclient.HttpClientService;
import wxdgaming.spring.boot.webclient.HttpPostTextWork;
import wxdgaming.spring.boot.webclient.WebClientScan;

@RunWith(SpringRunner.class)
@SpringBootApplication
@SpringBootTest(classes = {CoreScan.class, WebClientScan.class})
public class TestBase {

    @Autowired
    protected HttpClientService httpClientService;

    @Before
    public void before() {
        // VirtualExecutor virtualExecutor = new VirtualExecutor(200);
        // HttpClientBuild httpClientBuild = new HttpClientBuild();
        // httpClientService = new HttpClientService(virtualExecutor, httpClientBuild.httpClient());
    }

    public String getToken() {
        String accountName = "wxdgaming";
        String password = "87FE6B";

        HttpPostTextWork httpPostTextWork = httpClientService.doPostText("http://localhost:28081/account/login");
        httpPostTextWork.addRequestParam("accountName", accountName).addRequestParam("password", password);
        return RunResult.parse(httpPostTextWork.request().bodyString()).data();
    }

}
