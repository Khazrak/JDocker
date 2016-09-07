package se.codeslasher.docker;

import com.google.gson.GsonBuilder;
import org.junit.Test;

/**
 * Created by karl on 9/5/16.
 */
public class ContainerCreate {

    @Test
    public void createContainer() {
        DockerClient client = new DefaultDockerClient();

        HostConfig hostConfig = HostConfig.builder().build();

        ContainerCreation test = ContainerCreation.builder().name("Test_Container").image("ubuntu:14.04").hostConfig(hostConfig).build();

        GsonBuilder builder = new GsonBuilder();


        System.out.println(builder.create().toJson(test));


        client.close();
    }

}
