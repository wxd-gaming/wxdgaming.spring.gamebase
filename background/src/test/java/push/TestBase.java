package push;

import org.junit.Before;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.core.threading.ExecutorBuilder;
import wxdgaming.spring.boot.webclient.HttpClientBuild;
import wxdgaming.spring.boot.webclient.HttpClientService;
import wxdgaming.spring.boot.webclient.HttpPostTextWork;

public class TestBase {

    protected HttpClientService httpClientService;

    @Before
    public void before() {
        ExecutorBuilder executorBuilder = new ExecutorBuilder();
        HttpClientBuild httpClientBuild = new HttpClientBuild();
        httpClientService = new HttpClientService(executorBuilder.virtualExecutor(), httpClientBuild.httpClient());
    }

    public String getToken() {
        String accountName = "wxdgaming";
        String password = "87FE6B";

        HttpPostTextWork httpPostTextWork = httpClientService.doPostText("http://localhost:28081/account/login");
        httpPostTextWork.addRequestParam("accountName", accountName).addRequestParam("password", password);
        return RunResult.parse(httpPostTextWork.request().bodyString()).data();
    }

}
