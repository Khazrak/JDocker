package se.codeslasher.docker;

import java.io.InputStream;
import java.util.List;

/**
 * Created by karl on 9/5/16.
 */
public interface DockerClient {

    void close();

    String createContainer(ContainerCreation spec);
    void start(String id);
    void stop(String id);
    void stop(String id, int secondsUntilKill);
    void remove(String id);
    void remove(String id, boolean forceRemove, boolean removeVolume);
    void kill(String id);
    void kill(String id, String signal);
    List<Container> list();
    List<Container> list(ContainerListRequest listRequest);

    List<String> logs(String id, DockerLogsParameters params);
    InputStream logsRawStream(String id, DockerLogsParameters params);
    InputStream logsStream(String id, DockerLogsParameters params);
    DockerLogsLineReader logsSpecial(String id, DockerLogsParameters params);

    String pull(DockerImageName image);
    void pull(DockerImageName image, AuthConfig authConfig);
    void pull(DockerImageName image, String token);
}
