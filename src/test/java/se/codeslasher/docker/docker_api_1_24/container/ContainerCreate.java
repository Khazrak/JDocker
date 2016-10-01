package se.codeslasher.docker.docker_api_1_24.container;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import se.codeslasher.docker.model.api124.requests.ContainerCreationRequest;
import se.codeslasher.docker.DefaultDockerClient;
import se.codeslasher.docker.DockerClient;
import se.codeslasher.docker.model.api124.HostConfig;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by karl on 9/5/16.
 */
public class ContainerCreate {

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
    public void createContainer() throws JsonProcessingException {

        final String path = "/v1.24%2Fcontainers%2Fcreate?name=test_container";

        HostConfig hostConfig = HostConfig.builder().build();

        ContainerCreationRequest test = ContainerCreationRequest.builder().name("test_container").image("ubuntu:14.04").hostConfig(hostConfig).build();

        String id = client.createContainer(test);

        assertThat(id).isEqualTo("bd5ef2fe35da356ecc75527d77388149d7c016fcdfd3201429fe6a1b5a5e9308");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

}
