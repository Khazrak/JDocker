package se.codeslasher.docker;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.gson.GsonBuilder;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;



/**
 * Created by karl on 9/5/16.
 */
public class ContainerCreate {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(9779); // No-args constructor defaults



    @Test
    public void createContainer() {
        DockerClient client = new DefaultDockerClient();

        HostConfig hostConfig = HostConfig.builder().build();

        ContainerCreation test = ContainerCreation.builder().name("test_container").image("ubuntu:14.04").hostConfig(hostConfig).build();

        GsonBuilder builder = new GsonBuilder();


        System.out.println(builder.create().toJson(test));


        String id = client.createContainer(test);

        System.out.println(id);


        client.close();
    }

}
