package se.codeslasher.docker;

import org.junit.Test;

/**
 * Created by karl on 9/5/16.
 */
public class ContainerCreate {

    @Test
    public void createContainer() {
        DockerClient client = new DefaultDockerClient();

        client.containerBuilder();

        client.close();
    }

}
