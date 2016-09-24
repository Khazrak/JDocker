package se.codeslasher.docker;

import se.codeslasher.docker.model.api124.*;
import se.codeslasher.docker.handlers.DockerLogsLineReader;

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

    List<Container> listContainers();

    List<Container> listContainers(ContainerListRequest listRequest);

    DockerContainerInspect inspectContainer(String id, boolean size);

    ContainerProcesses top(String id);

    ContainerProcesses top(String id, String arg);

    List<ContainerFileSystemChange> containerFileSystemChanges(String id);

    ContainerStats stats(String id);

    InputStream statsStream(String id);

    void resizeTty(String id, int width, int height);

    List<String> logs(String id, DockerLogsParameters params);

    InputStream logsRawStream(String id, DockerLogsParameters params);

    InputStream logsStream(String id, DockerLogsParameters params);

    DockerLogsLineReader logsSpecial(String id, DockerLogsParameters params);

    String pull(DockerImageName image);

    void pull(DockerImageName image, AuthConfig authConfig);

    void pull(DockerImageName image, String token);

    void restart(String id);

    void restart(String id, int wait);

    Warnings update(String id, ContainerUpdateRequest updateConfig);

    void rename(String originalName, String newName);

    void pause(String id);

    void unpause(String id);

    List<Image> listImages(boolean all);

    List<Image> listImages(ListImagesParams params);

    String createNetwork(DockerNetworkCreateRequest request);

    List<Network> listNetworks();

    List<Network> listNetworks(NetworkListParams params);

    List<Volume> listVoume();


    void connectContainerToNetwork(NetworkConnectRequest request);

    void disconnectContainerFromNetwork(String containerName, String networkName, boolean force);
}
