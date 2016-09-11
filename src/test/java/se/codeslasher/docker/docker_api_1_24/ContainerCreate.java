package se.codeslasher.docker.docker_api_1_24;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import se.codeslasher.docker.ContainerCreation;
import se.codeslasher.docker.DefaultDockerClient;
import se.codeslasher.docker.DockerClient;
import se.codeslasher.docker.HostConfig;

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

        HostConfig hostConfig = HostConfig.builder().build();

        ContainerCreation test = ContainerCreation.builder().name("test_container").image("ubuntu:14.04").hostConfig(hostConfig).build();

        String id = client.createContainer(test);

        assertThat(id).isEqualTo("f2aca7ccb724d73aad6e4f6adc75fc3c8792df31ac5ec459cb857c0ca6f60d2c");
    }

}
