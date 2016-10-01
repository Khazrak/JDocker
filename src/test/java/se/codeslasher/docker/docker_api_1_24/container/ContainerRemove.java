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

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

/**
 * Created by karl on 9/10/16.
 */
public class ContainerRemove {

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
    public void remove() {
        final String path = "/%2Fv1.24%2Fcontainers%2Ff2aca7ccb724d73aad6e4f6";

        client.remove("f2aca7ccb724d73aad6e4f6");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.DELETE,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void removeForceAndVolume() {
        final String path = "/%2Fv1.24%2Fcontainers%2Ff2aca7ccb724d73aad6e4f1?force=true&v=true";

        client.remove("f2aca7ccb724d73aad6e4f1", true, true);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.DELETE,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void removeForce() {
        final String path = "/%2Fv1.24%2Fcontainers%2Ff2aca7ccb724d73aad6e4f1?force=true&v=false";

        client.remove("f2aca7ccb724d73aad6e4f1", true, false);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.DELETE,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void removeWithVolume() {
        final String path = "/%2Fv1.24%2Fcontainers%2Ff2aca7ccb724d73aad6e4f1?force=false&v=true";

        client.remove("f2aca7ccb724d73aad6e4f1", false, true);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.DELETE,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void removeNormal() {
        final String path = "/%2Fv1.24%2Fcontainers%2Ff2aca7ccb724d73aad6e4f1?force=false&v=false";

        client.remove("f2aca7ccb724d73aad6e4f1", false, false);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.DELETE,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

}
