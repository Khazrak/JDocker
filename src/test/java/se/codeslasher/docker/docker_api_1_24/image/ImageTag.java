package se.codeslasher.docker.docker_api_1_24.image;

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
import se.codeslasher.docker.utils.DockerImageName;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

/**
 * Created by karl on 10/2/16.
 */
public class ImageTag {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(ImageTag.class);

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
    public void tag() {

        final String path = "/v1.24%2Fimages%2Fmongo:latest%2Ftag?repo=test%2Fmongos&tag=5";

        DockerImageName original = new DockerImageName("mongo");
        DockerImageName newName = new DockerImageName("test/mongos:5");

        client.tagImage(original, newName);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);

    }

}
