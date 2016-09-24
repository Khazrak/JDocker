package se.codeslasher.docker.docker_api_1_24.network;

import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.DefaultDockerClient;
import se.codeslasher.docker.DockerClient;
import se.codeslasher.docker.docker_api_1_24.container.ContainerTop;
import se.codeslasher.docker.model.api124.Network;
import se.codeslasher.docker.model.api124.NetworkConnectRequest;
import se.codeslasher.docker.model.api124.NetworkDisconnectRequest;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by karl on 9/24/16.
 */
public class NetworkConnectDisconnect {
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
    public void connect() {
        final String path = "/v1.24/networks/test1/connect";

        NetworkConnectRequest request = NetworkConnectRequest.builder().networkName("test1").container("mongo").build();

        client.connectContainerToNetwork(request);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void connectWithEndpoint() {
        Assert.fail("Not implemented yet");
    }

    @Test
    public void disconnect() {
        final String path = "/v1.24/networks/test1/disconnect";

        client.disconnectContainerFromNetwork("mongo","test1",false);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

}
