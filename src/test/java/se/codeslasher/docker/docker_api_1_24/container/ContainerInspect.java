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
import se.codeslasher.docker.model.api124.DockerContainerInspect;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by karl on 9/20/16.
 */
public class ContainerInspect {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(ContainerInspect.class);

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
    public void inspect() {
        final String path = "/v1.24/containers/mongo/json?size=false";

        DockerContainerInspect mongo = client.inspectContainer("mongo", false);

        logger.info(mongo.getName());
        assertThat(mongo.getName()).isEqualTo("/mongo");
        assertThat(mongo.getSizeRootFs()).isEqualTo(0);
        assertThat(mongo.getConfig().getHostName()).isEqualTo("bd76acba6268");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void inspectSize() {
        final String path = "/v1.24/containers/mongo/json?size=true";

        DockerContainerInspect mongo = client.inspectContainer("mongo", true);

        logger.info(mongo.getName());
        assertThat(mongo.getName()).isEqualTo("/mongo");
        assertThat(mongo.getSizeRootFs()).isGreaterThan(0);
        assertThat(mongo.getConfig().getHostName()).isEqualTo("bd76acba6268");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

}
