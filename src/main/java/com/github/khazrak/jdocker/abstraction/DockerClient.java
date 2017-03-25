/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.khazrak.jdocker.abstraction;

import com.github.khazrak.jdocker.api126.requests.ListVolumeParams;
import com.github.khazrak.jdocker.utils.DockerImageName;
import com.github.khazrak.jdocker.utils.DockerLogsLineReader;
import com.github.khazrak.jdocker.utils.RequestStreamBody;

import java.io.InputStream;
import java.net.Proxy;
import java.util.List;

public interface DockerClient {

    void close();

    DockerVersion version();

    String ping();

    SystemInfo info();

    List<ImageInfo> listImages();

    List<ImageInfo> listImages(boolean all);

    List<ImageInfo> listImages(ListImagesParams params);


    InputStream pullImage(DockerImageName image);

    InputStream pullImage(DockerImageName image, String token);

    InputStream pullImage(DockerImageName image, AuthConfig authConfig);

    InputStream pushImage(DockerImageName imageToPush, AuthConfig authConfig);

    String removeImage(DockerImageName image);

    String removeImage(DockerImageName image, boolean force, boolean noprune);

    void imageTag(DockerImageName original, DockerImageName newTag);

    List<ImageSearchInfo> searchImage(String term);

    ImageInspect inspectImage(DockerImageName imageName);

    List<ImageHistoryInfo> imageHistory(DockerImageName name);

    InputStream buildImageFromArchive(BuildImageFromArchiveRequest request);

    InputStream buildImageFromRemote(BuildImageFromRemoteRequest request);

    InputStream getImageTar(DockerImageName imageName);

    String importImageTar(InputStream input, boolean quiet);

    String createNetwork(NetworkCreateRequest request);

    List<Network> listNetworks();

    List<Network> listNetworks(NetworkListParams params);

    void removeNetwork(String networkName);

    Network inspectNetwork(String networkName);

    void connectContainerToNetwork(NetworkConnectRequest networkConnectRequest);

    void disconnectContainerFromNetwork(String container, String network, boolean force);

    Volume createVolume(VolumeCreateRequest request);

    List<Volume> listVolumes();

    List<Volume> listVolumes(ListVolumeParams params);

    Volume inspectVolume(String volumeName);

    void removeVolume(String volumeName);

    String createExec(String containerId, ExecCreateRequest request);

    void startExec(String id, boolean tty);

    InputStream startExec(String id);

    ExecInfo inspectExec(String execId);

    void resizeExec(String execId, int w, int h);

    String createContainer(ContainerCreationRequest request);

    List<Container> listContainers();

    List<Container> listContainers(ListContainerParams request);

    void kill(String id);

    void kill(String id, String killSignal);

    ContainerInspect inspectContainer(String id, boolean size);

    void stop(String id);

    void stop(String id, int secondsUntilKill);

    void start(String id);

    List<String> logs(String id, DockerLogsParameters params);

    InputStream logsRawStream(String id, DockerLogsParameters params);

    InputStream logsStream(String id, DockerLogsParameters params);

    DockerLogsLineReader logsSpecial(String id, DockerLogsParameters params);

    void pause(String id);

    void unpause(String id);

    ContainerProcesses top(String id);

    ContainerProcesses top(String id, String args);

    void waitForContainerStop(String id);

    InputStream statsStream(String id);

    ContainerStats stats(String id);

    Warnings update(String id, ContainerUpdateRequest updateRequest);

    void restart(String id);

    void restart(String id, int wait);

    void rename(String originalName, String newName);

    void resizeTty(String id, int w, int h);

    void remove(String id);

    void remove(String id, boolean forceRemove, boolean removeVolume);

    FileSystemInfo fileSystemInfo(String id, String path);

    InputStream fileSystemArchiveDownload(String id, String path);

    void fileSystemArchiveUpload(String id, String path, RequestStreamBody body);

    String commitContainer(ContainerCommitRequest containerCommitRequest);

    List<ContainerFileSystemChange> containerFileSystemChanges(String id);

    void setProxy(Proxy proxy);
}
