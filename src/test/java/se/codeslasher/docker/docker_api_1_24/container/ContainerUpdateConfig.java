package se.codeslasher.docker.docker_api_1_24.container;

import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import se.codeslasher.docker.model.api124.requests.ContainerUpdateRequest;
import se.codeslasher.docker.DefaultDockerClient;
import se.codeslasher.docker.DockerClient;
import se.codeslasher.docker.model.api124.Warnings;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by karl on 9/22/16.
 */
public class ContainerUpdateConfig {

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
    public void update() {
        final String path = "/v1.24%2Fcontainers%2Fmongo%2Fupdate";

        ContainerUpdateRequest update = ContainerUpdateRequest.builder().cpuShares(2).build();

        Warnings warnings = client.update("mongo", update);

        assertThat(warnings.getWarnings()).isNull();

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void updateWithWarnings() {
        final String path = "/v1.24%2Fcontainers%2Fmongo%2Fupdate";

        ContainerUpdateRequest update = ContainerUpdateRequest.builder().cpuShares(3).build();

        Warnings warnings = client.update("mongo", update);

        assertThat(warnings.getWarnings()).isNotNull();
        assertThat(warnings.size()).isEqualTo(3);
        assertThat(warnings.getWarning(0)).isEqualTo("This is a test warning");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

}
