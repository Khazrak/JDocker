package se.codeslasher.docker;

import se.codeslasher.docker.handlers.DockerLogsLineReader;
import se.codeslasher.docker.model.api124.*;
import se.codeslasher.docker.model.api124.parameters.*;
import se.codeslasher.docker.model.api124.requests.*;
import se.codeslasher.docker.utils.DockerImageName;

import java.io.InputStream;
import java.util.List;

/**
 * Created by karl on 9/5/16.
 */
public interface DockerClient {

    void close();

    String createContainer(ContainerCreationRequest spec);

    void start(String id);

    void stop(String id);

    void stop(String id, int secondsUntilKill);

    void remove(String id);

    void remove(String id, boolean forceRemove, boolean removeVolume);

    void kill(String id);

    void kill(String id, String signal);

    List<Container> listContainers();

    List<Container> listContainers(ListContainerParams listRequest);

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

    String pull(DockerImageName image, AuthConfig authConfig);

    String pull(DockerImageName image, String token);

    void restart(String id);

    void restart(String id, int wait);

    Warnings update(String id, ContainerUpdateRequest updateConfig);

    void rename(String originalName, String newName);

    void pause(String id);

    void unpause(String id);

    List<ImageInfo> listImages(boolean all);

    List<ImageInfo> listImages(ListImagesParams params);

    String createNetwork(NetworkCreateRequest request);

    List<Network> listNetworks();

    List<Network> listNetworks(NetworkListParams params);

    void connectContainerToNetwork(NetworkConnectRequest request);

    void disconnectContainerFromNetwork(String containerName, String networkName, boolean force);

    Network inspectNetwork(String id);

    void removeNetwork(String id);

    List<Volume> listVolumes();

    List<Volume> listVolumes(ListVolumeParams params);

    Volume createVolume(VolumeCreateRequest request);

    Volume inspectVolume(String id);

    void removeVolume(String id);

    Image inspectImage(DockerImageName imageName);

    ExecInfo inspectExec(String id);

    String createExec(String containerId, ExecCreateRequest request);

    void resizeExec(String id, int width, int height);

    void startExec(String id, boolean tty);

    InputStream startExec(String id);

    void tagImage(DockerImageName original, DockerImageName newName);
}
