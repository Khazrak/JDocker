package se.codeslasher.docker.docker_api_1_24.volume;

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
import se.codeslasher.docker.model.api124.NetworkListParams;
import se.codeslasher.docker.model.api124.Volume;
import se.codeslasher.docker.model.api124.VolumeListParams;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by karl on 9/23/16.
 */
public class VolumeList {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(VolumeList.class);

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
        final String path = "/v1.24/volumes";

        List<Volume> volumeList = client.listVolumes();

        assertThat(volumeList.size()).isEqualTo(26);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void paramsDanglingUsage() {
        VolumeListParams params = VolumeListParams.builder().dangling(false).build();
        assertThat(params.isUseDangling()).isTrue();
        assertThat(params.isDangling()).isFalse();

        VolumeListParams params2 = VolumeListParams.builder().dangling(true).build();
        assertThat(params2.isUseDangling()).isTrue();
        assertThat(params2.isDangling()).isTrue();

        VolumeListParams params3 = VolumeListParams.builder().build();
        assertThat(params3.isUseDangling()).isFalse();
        assertThat(params3.isDangling()).isFalse();
    }

    @Test
    public void listFiltersDanglingTrue() {

        final String path = "/v1.24/volumes?filter=%7B%22dangling%22%3A%7B%22true%22%3Atrue%7D%7D";

        VolumeListParams params = VolumeListParams.builder().dangling(true).build();

        List<Volume> volumeList = client.listVolumes(params);

        assertThat(volumeList.size()).isEqualTo(26);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);

    }

    @Test
    public void listFiltersDanglingFalse() {

        final String path = "/v1.24/volumes?filter=%7B%22dangling%22%3A%7B%22false%22%3Atrue%7D%7D";

        VolumeListParams params = VolumeListParams.builder().dangling(false).build();

        List<Volume> volumeList = client.listVolumes(params);

        assertThat(volumeList.size()).isEqualTo(26);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);

    }

}
