package se.codeslasher.docker;

/**
 * Created by karl on 9/5/16.
 */
public interface DockerClient {

    void close();

    String createContainer(ContainerCreation spec);
}
