package se.codeslasher.docker.docker_api_1_24.network;

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
import se.codeslasher.docker.docker_api_1_24.container.ContainerTop;
import se.codeslasher.docker.model.api124.Network;
import se.codeslasher.docker.model.api124.NetworkInterface;
import se.codeslasher.docker.model.api124.NetworkListParams;
import se.codeslasher.docker.utils.Filters;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by karl on 9/23/16.
 */
public class NetworkList {

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
    public void list() {
        final String path = "/v1.24/networks";

        List<Network> networksList = client.listNetworks();

        assertThat(networksList.size()).isEqualTo(4);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void listByname() {
        final String path = "/v1.24/networks?filters=%7B%22name%22%3A%7B%22test1%22%3Atrue%7D%7D";

        NetworkListParams params = NetworkListParams.builder().name("test1").build();

        List<Network> networksList = client.listNetworks(params);

        assertThat(networksList.size()).isEqualTo(1);
        assertThat(networksList.get(0).getName()).isEqualTo("test1");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void listByDriver() {
        final String path = "/v1.24/networks?filters=%7B%22driver%22%3A%7B%22bridge%22%3Atrue%7D%7D";

        NetworkListParams params = NetworkListParams.builder().driver("bridge").build();

        List<Network> networksList = client.listNetworks(params);

        assertThat(networksList.size()).isEqualTo(2);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

}
