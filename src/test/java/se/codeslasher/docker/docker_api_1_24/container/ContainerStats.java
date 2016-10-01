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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by karl on 9/22/16.
 */
public class ContainerStats {

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
    public void stats() {
        final String path = "/v1.24%2Fcontainers%2Fmongo%2Fstats?stream=false";

        se.codeslasher.docker.model.api124.ContainerStats mongoStats = client.stats("mongo");

        long systemCpuUsage = 60897810000000L;

        assertThat(mongoStats.getCpuStats().getSystemCpuUsage()).isEqualTo(systemCpuUsage);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void statsStream() {
        final String path = "/v1.24%2Fcontainers%2Fmongo%2Fstats?stream=true";

        InputStream input = client.statsStream("mongo");
        int count = 0;
        int expected = 59;

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            while(reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(count).isEqualTo(expected);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }
}
