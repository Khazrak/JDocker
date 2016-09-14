package se.codeslasher.docker.docker_api_1_24;

import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import org.junit.*;
import se.codeslasher.docker.*;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by karl on 9/10/16.
 */
public class ContainerList {

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
    public void list() {
        List<Container> containerList = client.list();

        assertThat(containerList.size()).isEqualTo(2);

        DockerImageName mongo = new DockerImageName("mongo");

        Container one = containerList.get(0);
        Container two = containerList.get(1);

        assertThat(one.getImage()).isEqualTo(mongo);
        assertThat(two.getImage()).isEqualTo(mongo);

        UrlPattern pattern = UrlPattern.fromOneOf("/v1.24/containers/json", null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void listAll() {
        ContainerListRequest request = ContainerListRequest.builder().all(true).build();
        List<Container> containerList = client.list(request);

        assertThat(containerList.size()).isEqualTo(3);

        DockerImageName mongo = new DockerImageName("mongo");
        DockerImageName ubuntu = new DockerImageName("ubuntu:14.04");

        Container one = containerList.get(0);
        Container two = containerList.get(1);
        Container three = containerList.get(2);

        assertThat(one.getImage()).isEqualTo(mongo);
        assertThat(two.getImage()).isEqualTo(mongo);
        assertThat(three.getImage()).isEqualTo(ubuntu);

        UrlPattern pattern = UrlPattern.fromOneOf("/v1.24/containers/json?all=true", null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void listSince() {
        Assert.fail("Not implemented yet");
    }

    @Test
    public void listBefore() {
        Assert.fail("Not implemented yet");
    }

    @Test
    public void listLimit() {
        ContainerListRequest request = ContainerListRequest.builder().limit(1).build();
        List<Container> containerList = client.list(request);


        assertThat(containerList.size()).isEqualTo(1);

        DockerImageName mongo = new DockerImageName("mongo");

        Container one = containerList.get(0);

        assertThat(one.getImage()).isEqualTo(mongo);

        UrlPattern pattern = UrlPattern.fromOneOf("/v1.24/containers/json?limit=1", null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void listSize() {
        ContainerListRequest request = ContainerListRequest.builder().size(true).build();
        List<Container> containerList = client.list(request);

        assertThat(containerList.size()).isEqualTo(2);

        DockerImageName mongo = new DockerImageName("mongo");

        Container one = containerList.get(0);
        Container two = containerList.get(1);

        assertThat(one.getImage()).isEqualTo(mongo);
        assertThat(two.getImage()).isEqualTo(mongo);

        UrlPattern pattern = UrlPattern.fromOneOf("/v1.24/containers/json?size=true", null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void listFilter() {
        Assert.fail("Not implemented yet");
    }



}
