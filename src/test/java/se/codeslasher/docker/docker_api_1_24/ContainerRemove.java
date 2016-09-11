package se.codeslasher.docker.docker_api_1_24;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import se.codeslasher.docker.DefaultDockerClient;
import se.codeslasher.docker.DockerClient;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

/**
 * Created by karl on 9/10/16.
 */
public class ContainerRemove {

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
    public void remove() {
        client.remove("f2aca7ccb724d73aad6e4f6");
    }

    @Test
    public void removeForceAndVolume() {
        client.remove("f2aca7ccb724d73aad6e4f1", true, true);
    }

    @Test
    public void removeForce() {
        client.remove("f2aca7ccb724d73aad6e4f1", true, false);
    }

    @Test
    public void removeWithVolume() {
        client.remove("f2aca7ccb724d73aad6e4f1", false, true);
    }

    @Test
    public void removeNormal() {
        client.remove("f2aca7ccb724d73aad6e4f1", false, false);
    }

}
