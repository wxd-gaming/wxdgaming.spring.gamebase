package code;

import org.junit.Before;
import wxdgaming.spring.boot.core.lang.RunResult;
import wxdgaming.spring.boot.core.threading.ExecutorBuilder;
import wxdgaming.spring.boot.webclient.HttpClientBuild;
import wxdgaming.spring.boot.webclient.HttpClientService;
import wxdgaming.spring.boot.webclient.HttpPostTextWork;

public class BaseTest {

    protected int port = 8081;
    protected String accountName = "wxdgaming";
    protected String password = "87FE6B";

    protected HttpClientService httpClientService;

    @Before
    public void before() {
        ExecutorBuilder executorBuilder = new ExecutorBuilder();
        HttpClientBuild httpClientBuild = new HttpClientBuild();
        httpClientService = new HttpClientService(executorBuilder.virtualExecutor(), httpClientBuild.httpClient());
    }

    public String getToken() {
        HttpPostTextWork httpPostTextWork = httpClientService.doPostText(String.format("http://localhost:%s/account/login", port));
        httpPostTextWork.addRequestParam("accountName", accountName).addRequestParam("password", password);
        RunResult parse = RunResult.parse(httpPostTextWork.request().bodyString());
        if (parse.code() != 1) throw new RuntimeException(parse.errorMsg());
        return parse.data();
    }

}
