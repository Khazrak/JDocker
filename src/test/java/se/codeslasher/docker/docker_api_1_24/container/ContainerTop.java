package se.codeslasher.docker.docker_api_1_24.container;

import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.DefaultDockerClient;
import se.codeslasher.docker.DockerClient;
import se.codeslasher.docker.model.api124.ContainerProcesses;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by karl on 9/21/16.
 */
public class ContainerTop {
    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(ContainerTop.class);

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
    public void top() {
        final String path = "/v1.24%2Fcontainers%2Fmongo%2Ftop";

        ContainerProcesses top = client.top("mongo");


        String c = top.getProcesses().get(0).get(3);
        assertThat(c).isEqualTo("0");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void topWithArgument() {
        final String path = "/v1.24%2Fcontainers%2Fmongo%2Ftop?ps_args=aux";

        ContainerProcesses top = client.top("mongo","aux");

        String start = top.getProcesses().get(0).get(8);
        assertThat(start).isEqualTo("19:29");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }


}
