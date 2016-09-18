package se.codeslasher.docker.docker_api_1_24;

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
import se.codeslasher.docker.model.api124.DockerImageName;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by karl on 9/18/16.
 */
public class ContainerPull {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(ContainerPull.class);

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
    public void pull() {

        DockerImageName image = new DockerImageName("busybox");
        String response = client.pull(image);

        logger.info(response);

        assertThat(response).contains("{\"status\":\"Status: Downloaded newer image for busybox:latest\"}");

        UrlPattern pattern = UrlPattern.fromOneOf("/v1.24/images/create?fromImage=busybox&tag=latest", null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);


    }

    //@Test
    public void pullAuth() {
        //TODO: private Registry pull with login
    }

    //@Test
    public void pullToken() {
        //TODO: private Registry with token
    }

    @Test
    public void pullUbuntu() {
        DockerImageName image = new DockerImageName("ubuntu:16.04");
        String response = client.pull(image);

        logger.info(response);

        assertThat(response).contains("{\"status\":\"Status: Downloaded newer image for ubuntu:16.04\"}");

        UrlPattern pattern = UrlPattern.fromOneOf("/v1.24/images/create?fromImage=ubuntu&tag=16.04", null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

}
