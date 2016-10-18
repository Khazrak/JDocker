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
package se.codeslasher.docker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.handlers.*;
import se.codeslasher.docker.model.api124.*;
import se.codeslasher.docker.model.api124.parameters.*;
import se.codeslasher.docker.model.api124.requests.*;
import se.codeslasher.docker.ssl.DockerSSLSocket;
import se.codeslasher.docker.ssl.SslSocketConfigFactory;
import se.codeslasher.docker.unixsocket.UnixSocketFactory;
import se.codeslasher.docker.utils.*;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DefaultDockerClient implements DockerClient {

    private static Logger logger = LoggerFactory.getLogger(DefaultDockerClient.class);

    private OkHttpClient httpClient;

    private final String URL;

    private DockerImagesHandler imageHandler;
    private DockerNetworksHandler networksHandler;
    private DockerContainerHandler containerHandler;
    private DockerVolumesHandler volumesHandler;
    private DockerExecHandler execHandler;

    private ObjectMapper mapper;

    public DefaultDockerClient() {
        //httpClient = new OkHttpClient();
        URLResolver urlResolver;

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if(UnixSocketFactory.isSupported()) {
            UnixSocketFactory unixSocketFactory = new UnixSocketFactory();
            builder = builder.socketFactory(unixSocketFactory);
            builder = builder.dns(unixSocketFactory);
            URL = "/var/run/docker.sock";
            urlResolver = new UnixURLResolver(unixSocketFactory);
        }
        else {
            URL = "http://127.0.0.1:4243";
            urlResolver = new HttpURLResolver();
        }

        httpClient = builder
                .connectTimeout(30, TimeUnit.MINUTES)
                .readTimeout(0, TimeUnit.SECONDS)
                .writeTimeout(0, TimeUnit.SECONDS)
                .build();



        mapper = getMapper();

        imageHandler = new DockerImagesHandler(httpClient, urlResolver, mapper, URL);
        networksHandler = new DockerNetworksHandler(httpClient, urlResolver,mapper, URL);
        containerHandler = new DockerContainerHandler(httpClient, urlResolver,mapper, URL);
        volumesHandler = new DockerVolumesHandler(httpClient, urlResolver,mapper, URL);
        execHandler = new DockerExecHandler(httpClient, urlResolver, mapper, URL);
    }

    public DefaultDockerClient(String host) {

        httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.MINUTES)
                .readTimeout(0, TimeUnit.SECONDS)
                .writeTimeout(0, TimeUnit.SECONDS)
                .build();

        httpClient = new OkHttpClient();

        URL = host;
        mapper = getMapper();

        URLResolver urlResolver = new HttpURLResolver();
        imageHandler = new DockerImagesHandler(httpClient, urlResolver, mapper, URL);
        networksHandler = new DockerNetworksHandler(httpClient, urlResolver, mapper, URL);
        containerHandler = new DockerContainerHandler(httpClient, urlResolver, mapper, URL);
        volumesHandler = new DockerVolumesHandler(httpClient, urlResolver, mapper, URL);
        execHandler = new DockerExecHandler(httpClient, urlResolver, mapper, URL);
    }

    public DefaultDockerClient(String host, String certPath) throws IOException, GeneralSecurityException {

        URL = host;

        DockerSSLSocket dockerSSLSocket = new SslSocketConfigFactory().createDockerSslSocket(certPath);

        httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.MINUTES)
                .readTimeout(0, TimeUnit.SECONDS)
                .writeTimeout(0, TimeUnit.SECONDS)
                .sslSocketFactory(dockerSSLSocket.getSslSocketFactory(), dockerSSLSocket.getTrustManager())
                .build();

        mapper = getMapper();

        //SerializationFeature.FAIL_ON_EMPTY_BEANS

        URLResolver urlResolver = new HttpURLResolver();
        imageHandler = new DockerImagesHandler(httpClient, urlResolver, mapper, URL);
        networksHandler = new DockerNetworksHandler(httpClient, urlResolver, mapper, URL);
        containerHandler = new DockerContainerHandler(httpClient, urlResolver, mapper, URL);
        volumesHandler = new DockerVolumesHandler(httpClient, urlResolver, mapper, URL);
        execHandler = new DockerExecHandler(httpClient, urlResolver, mapper, URL);
    }

    private ObjectMapper getMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return objectMapper;
    }

    @Override
    public void close()  {
        httpClient = null;
    }

    @Override
    public AuthTestResponse auth(AuthTestRequest request) {
        return containerHandler.auth(request);
    }

    @Override
    public void waitForContainerStop(String id) {
        containerHandler.waitForContainerStop(id);
    }

    @Override
    public String commitContainer(ContainerCommitRequest containerCommitRequest) {
        return containerHandler.commitContainer(containerCommitRequest);
    }

    @Override
    public FileSystemInfo fileSystemInfo(String id, String path) {
        return containerHandler.fileSystemInfo(id, path);
    }

    @Override
    public InputStream fileSystemArchiveDownload(String id, String path) {
        return containerHandler.fileSystemArchiveDownload(id, path);
    }

    @Override
    public void fileSystemArchiveUpload(String id, String pathInContainer, RequestStreamBody body) {
        containerHandler.fileSystemArchiveUpload(id, pathInContainer, body);
    }

    @Override
    public String ping() {
        return containerHandler.ping();
    }

    @Override
    public DockerVersion version() {
        return containerHandler.version();
    }

    @Override
    public SystemInfo info() {
        return containerHandler.info();
    }

    @Override
    public String createContainer(ContainerCreationRequest spec) {
        return containerHandler.createContainer(spec);
    }

    @Override
    public DockerContainerInspect inspectContainer(String id, boolean size) {
        return containerHandler.inspectContainer(id,size);
    }

    @Override
    public ContainerProcesses top(String id) {
        return top(id,null);
    }

    @Override
    public ContainerProcesses top(String id, String arg) {
        return containerHandler.top(id,arg);
    }

    @Override
    public List<ContainerFileSystemChange> containerFileSystemChanges(String id) {
        return containerHandler.containerFileSystemChanges(id);
    }

    @Override
    public ContainerStats stats(String id) {
        return containerHandler.stats(id);
    }

    @Override
    public InputStream statsStream(String id) {
        return containerHandler.statsStream(id);
    }

    @Override
    public void resizeTty(String id, int width, int height) {
        containerHandler.resizeTty(id,width,height);
    }

    @Override
    public void start(String id) {
        containerHandler.start(id);
    }

    @Override
    public void stop(String id) {
        stop(id,10);
    }

    @Override
    public void stop(String id, int secondsUntilKill) {
        containerHandler.stop(id,secondsUntilKill);
    }

    @Override
    public void remove(String id) {
        containerHandler.remove(id);
    }

    @Override
    public void remove(String id, boolean forceRemove, boolean removeVolume) {
        containerHandler.remove(id,forceRemove,removeVolume);
    }

    @Override
    public void kill(String id) {
        containerHandler.kill(id);
    }

    @Override
    public void kill(String id, String signal) {
        containerHandler.kill(id,signal);
    }

    @Override
    public List<Container> listContainers() {
        return containerHandler.listContainers();
    }

    @Override
    public List<Container> listContainers(ListContainerParams listRequest) {
        return containerHandler.listContainers(listRequest);
    }

    @Override
    public List<String> logs(String id, DockerLogsParameters params) {
        return containerHandler.logs(id, params);
    }

    @Override
    public DockerLogsLineReader logsSpecial(String id, DockerLogsParameters params) {
        return containerHandler.logsSpecial(id, params);
    }

    @Override
    public InputStream logsRawStream(String id, DockerLogsParameters params) {
        return containerHandler.logsRawStream(id, params);
    }

    @Override
    public InputStream pullImage(DockerImageName image) {
        return imageHandler.pullImage(image);
    }

    @Override
    public InputStream pullImage(DockerImageName image, AuthConfig authConfig) {
        return imageHandler.pullImage(image, authConfig);
    }

    @Override
    public InputStream pullImage(DockerImageName image, String token) {
        return imageHandler.pullImage(image,token);
    }

    @Override
    public void restart(String id) {
        restart(id,10);
    }

    @Override
    public void restart(String id, int wait) {
        containerHandler.restart(id,wait);
    }

    @Override
    public Warnings update(String id, ContainerUpdateRequest updateConfig) {
        return containerHandler.update(id, updateConfig);
    }

    @Override
    public void rename(String originalName, String newName) {
        containerHandler.rename(originalName, newName);
    }

    @Override
    public void pause(String id) {
        containerHandler.pause(id);
    }

    @Override
    public void unpause(String id) {
        containerHandler.unpause(id);
    }

    @Override
    public List<ImageInfo> listImages(boolean all) {
        return imageHandler.listImages(all);
    }

    @Override
    public List<ImageInfo> listImages(ListImagesParams params) {
        return imageHandler.listImages(params);
    }

    @Override
    public String createNetwork(NetworkCreateRequest request) {
        return networksHandler.createNetwork(request);
    }

    @Override
    public List<Network> listNetworks() {
        return networksHandler.listNetworks();
    }

    @Override
    public List<Network> listNetworks(NetworkListParams params) {
        return networksHandler.listNetworks(params);
    }

    @Override
    public void connectContainerToNetwork(NetworkConnectRequest request) {
        networksHandler.connectContainerToNetwork(request);
    }

    @Override
    public void disconnectContainerFromNetwork(String containerName, String networkName, boolean force) {
        networksHandler.disconnectContainerFromNetwork(containerName, networkName, force);
    }

    @Override
    public Network inspectNetwork(String id) {
        return networksHandler.inspectNetwork(id);
    }

    @Override
    public void removeNetwork(String id) {
        networksHandler.removeNetwork(id);
    }

    @Override
    public InputStream logsStream(String id, DockerLogsParameters params) {
        return containerHandler.logsStream(id,params);
    }

    @Override
    public List<Volume> listVolumes() {
        return volumesHandler.listVolumes();
    }

    @Override
    public List<Volume> listVolumes(ListVolumeParams params) {
        return volumesHandler.listVolumes(params);
    }

    @Override
    public Volume createVolume(VolumeCreateRequest request) {
        return volumesHandler.createVolume(request);
    }

    @Override
    public Volume inspectVolume(String id) {
        return volumesHandler.inspectVolume(id);
    }

    @Override
    public void removeVolume(String id) {
        volumesHandler.removeVolume(id);
    }

    @Override
    public Image inspectImage(DockerImageName imageName) {
        return imageHandler.inspectImage(imageName);
    }

    @Override
    public ExecInfo inspectExec(String id) {
        return execHandler.inspectExec(id);
    }

    @Override
    public String createExec(String containerId, ExecCreateRequest request) {
        return execHandler.createExec(containerId, request);
    }

    @Override
    public void resizeExec(String id, int width, int height) {
        execHandler.resizeExec(id,width,height);
    }

    @Override
    public void startExec(String id, boolean tty) {
        execHandler.startExec(id,tty);
    }

    @Override
    public InputStream startExec(String id) {
        return execHandler.startExec(id);
    }

    @Override
    public void tagImage(DockerImageName original, DockerImageName newName) {
        imageHandler.tagImage(original, newName);
    }

    @Override
    public InputStream pushImage(DockerImageName imageToPush, AuthConfig authConfig) {
        return imageHandler.pushImage(imageToPush, authConfig);
    }

    @Override
    public InputStream pushImage(DockerImageName imageToPush, String identityToken) {
        return imageHandler.pushImage(imageToPush, identityToken);
    }

    @Override
    public String removeImage(DockerImageName name) {
        return imageHandler.removeImage(name, false, false);
    }

    @Override
    public String removeImage(DockerImageName name, boolean force, boolean noprune) {
        return imageHandler.removeImage(name,force,noprune);
    }

    @Override
    public List<ImageSearchInfo> searchImage(String term) {
        return imageHandler.searchImage(term);
    }

    @Override
    public List<ImageHistoryInfo> historyOfImage(DockerImageName name) {
        return imageHandler.historyOfImage(name);
    }

    @Override
    public InputStream buildImageFromRemote(BuildImageFromRemoteRequest request) {
        return imageHandler.buildImageFromRemote(request);
    }

    @Override
    public InputStream buildImageFromArchive(BuildImageFromArchiveRequest request) {
        return imageHandler.buildImageFromArchive(request);
    }

}
