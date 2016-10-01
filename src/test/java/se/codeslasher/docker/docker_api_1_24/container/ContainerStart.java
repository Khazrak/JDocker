package se.codeslasher.docker.docker_api_1_24.container;

import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import se.codeslasher.docker.DefaultDockerClient;
import se.codeslasher.docker.DockerClient;
import se.codeslasher.docker.exception.DockerClientException;
import se.codeslasher.docker.exception.DockerServerException;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

/**
 * Created by karl on 9/10/16.
 */
public class ContainerStart {

    private DockerClient client;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().usingFilesUnderClasspath("src/test/resources/1_24").port(9779)); // No-args constructor defaults

    @Before
    public void setup() {
        client = new DefaultDockerClient("http://127.0.0.1:9779");
    }

    @After
    public void tearDown() {
        client.close();
    }

    @Test
    public void start() {
        final String path = "/v1.24%2Fcontainers%2Ff2aca7ccb724d73aad6e4f6%2Fstart";

        client.start("f2aca7ccb724d73aad6e4f6");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test(expected = DockerClientException.class)
    public void startNoneExisting() {
        final String path = "/v1.24%2Fcontainers%2Fnone_existing%2Fstart";

        client.start("none_existing");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void startAlreadyStarted() {
        final String path = "/v1.24%2Fcontainers%2Falready_started%2Fstart";

        //Will log 304
        client.start("already_started");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

}
