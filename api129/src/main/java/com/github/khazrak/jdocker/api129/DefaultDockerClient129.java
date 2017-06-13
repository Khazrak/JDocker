package com.github.khazrak.jdocker.api129;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.khazrak.jdocker.abstraction.*;
import com.github.khazrak.jdocker.api129.handlers.*;
import com.github.khazrak.jdocker.api129.model.*;
import com.github.khazrak.jdocker.api129.requests.ContainerCommitRequestMount129;
import com.github.khazrak.jdocker.api129.requests.NetworkCreateIpamConfig129;
import com.github.khazrak.jdocker.ssl.DockerSSLSocket;
import com.github.khazrak.jdocker.ssl.SslSocketConfigFactory;
import com.github.khazrak.jdocker.unixsocket.NpipeSocketFactory;
import com.github.khazrak.jdocker.unixsocket.UnixSocketFactory;
import com.github.khazrak.jdocker.utils.*;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DefaultDockerClient129 implements DockerClient {

    public static final String API_VERSION = "v1.29";

    private OkHttpClient httpClient;

    private final String url;
    private final URLResolver urlResolver;

    private DockerMiscHandler miscHandler;
    private DockerImageHandler imageHandler;
    private DockerNetworkHandler networkHandler;
    private DockerVolumeHandler volumeHandler;
    private DockerExecHandler execHandler;
    private DockerContainerHandler containerHandler;

    private ObjectMapper mapper;

    public DefaultDockerClient129() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (UnixSocketFactory.isSupported()) {
            UnixSocketFactory unixSocketFactory = new UnixSocketFactory("/var/run/docker.sock");
            builder = builder.socketFactory(unixSocketFactory);
            builder = builder.dns(unixSocketFactory);
            url = "/var/run/docker.sock";
            urlResolver = new UnixURLResolver(unixSocketFactory);
        } else if (NpipeSocketFactory.isSupported()) {
            NpipeSocketFactory npipeSocketFactory = new NpipeSocketFactory();
            builder = builder.socketFactory(npipeSocketFactory);
            builder = builder.dns(npipeSocketFactory);
            url = "\\\\.\\pipe/docker_engine";
            urlResolver = new NpipeURLResolver(npipeSocketFactory);
        } else {
            url = "http://127.0.0.1:4243";
            urlResolver = new HttpURLResolver();
        }

        httpClient = builder
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(0, TimeUnit.SECONDS)
                .writeTimeout(0, TimeUnit.SECONDS)
                .build();

        mapper = getMapper();

        createHandlers(httpClient, urlResolver, mapper);

    }

    public DefaultDockerClient129(String host) {

        httpClient = new OkHttpClient.Builder()
                .connectTimeout(0, TimeUnit.MINUTES)
                .readTimeout(0, TimeUnit.SECONDS)
                .writeTimeout(0, TimeUnit.SECONDS)
                .build();

        url = host;
        mapper = getMapper();

        urlResolver = new HttpURLResolver();
        createHandlers(httpClient, urlResolver, mapper);
    }

    public DefaultDockerClient129(String host, String certPath) throws IOException, GeneralSecurityException {

        url = host;

        DockerSSLSocket dockerSSLSocket = new SslSocketConfigFactory().createDockerSslSocket(certPath);

        httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.MINUTES)
                .readTimeout(0, TimeUnit.SECONDS)
                .writeTimeout(0, TimeUnit.SECONDS)
                .sslSocketFactory(dockerSSLSocket.getSslSocketFactory(), dockerSSLSocket.getTrustManager())
                .build();

        mapper = getMapper();

        //SerializationFeature.FAIL_ON_EMPTY_BEANS

        urlResolver = new HttpURLResolver();
        createHandlers(httpClient, urlResolver, mapper);
    }

    public void setProxy(Proxy proxy) {
        httpClient = httpClient.newBuilder()
                .proxy(proxy)
                .build();

        createHandlers(httpClient, urlResolver, mapper);
    }

    private ObjectMapper getMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        SimpleModule module = new SimpleModule();
        module.addAbstractTypeMapping(IndexConfig.class, IndexConfig129.class);
        module.addAbstractTypeMapping(ImageInfo.class, ImageInfo129.class);
        module.addAbstractTypeMapping(ImageSearchInfo.class, ImageSearchInfo129.class);
        module.addAbstractTypeMapping(HostPort.class, HostPort129.class);
        module.addAbstractTypeMapping(Ulimit.class, Ulimit129.class);
        module.addAbstractTypeMapping(Mount.class, Mount129.class);
        module.addAbstractTypeMapping(NetworkInterface.class, NetworkInterface129.class);
        module.addAbstractTypeMapping(ImageInspect.class, ImageInspect129.class);
        module.addAbstractTypeMapping(ImageHistoryInfo.class, ImageHistoryInfo129.class);
        module.addAbstractTypeMapping(NetworkCreateIpamConfig.class, NetworkCreateIpamConfig129.class);
        module.addAbstractTypeMapping(NetworkContainer.class, NetworkContainer129.class);
        module.addAbstractTypeMapping(NetworkIPAMConfig.class, NetworkIPAMConfig129.class);
        module.addAbstractTypeMapping(Network.class, Network129.class);
        module.addAbstractTypeMapping(Volume.class, Volume129.class);
        module.addAbstractTypeMapping(ExecInfo.class, ExecInfo129.class);
        module.addAbstractTypeMapping(Container.class, Container129.class);
        module.addAbstractTypeMapping(ContainerPort.class, ContainerPort129.class);
        module.addAbstractTypeMapping(ContainerInspect.class, ContainerInspect129.class);
        module.addAbstractTypeMapping(ContainerProcesses.class, ContainerProcesses129.class);
        module.addAbstractTypeMapping(NetworkStats.class, NetworkStats129.class);
        module.addAbstractTypeMapping(IOServiceBytes.class, IOServiceBytes129.class);
        module.addAbstractTypeMapping(ContainerStats.class, ContainerStats129.class);
        module.addAbstractTypeMapping(Warnings.class, Warnings129.class);
        module.addAbstractTypeMapping(FileSystemInfo.class, FileSystemInfo129.class);
        module.addAbstractTypeMapping(ContainerCommitRequestMount.class, ContainerCommitRequestMount129.class);
        module.addAbstractTypeMapping(ContainerFileSystemChange.class, ContainerFileSystemChange129.class);

        objectMapper.registerModule(module);

        return objectMapper;
    }

    private void createHandlers(OkHttpClient httpClient, URLResolver urlResolver, ObjectMapper mapper) {

        OkHttpExecuter okHttpExecuter = new OkHttpExecuter(httpClient, url, urlResolver);

        miscHandler = new DockerMiscHandler(okHttpExecuter, mapper);
        imageHandler = new DockerImageHandler(okHttpExecuter, mapper);
        networkHandler = new DockerNetworkHandler(okHttpExecuter, mapper);
        volumeHandler = new DockerVolumeHandler(okHttpExecuter, mapper);
        execHandler = new DockerExecHandler(okHttpExecuter, mapper);
        containerHandler = new DockerContainerHandler(okHttpExecuter, mapper);
    }

    @Override
    public void close() {
        httpClient = null;
    }

    @Override
    public DockerVersion version() {
        return miscHandler.version();
    }

    @Override
    public String ping() {
        return miscHandler.ping();
    }

    @Override
    public SystemInfo info() {
        return miscHandler.info();
    }

    @Override
    public List<ImageInfo> listImages() {
        return listImages(false);
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
    public InputStream pullImage(DockerImageName image) {
        return imageHandler.pullImage(image);
    }

    @Override
    public InputStream pullImage(DockerImageName image, String token) {
        return imageHandler.pullImage(image, token);
    }

    @Override
    public InputStream pullImage(DockerImageName image, AuthConfig authConfig) {
        return imageHandler.pullImage(image, authConfig);
    }

    @Override
    public InputStream pushImage(DockerImageName imageToPush, AuthConfig authConfig) {
        return imageHandler.pushImage(imageToPush, authConfig);
    }

    @Override
    public String removeImage(DockerImageName image) {
        return imageHandler.removeImage(image, false, false);
    }

    @Override
    public String removeImage(DockerImageName image, boolean force, boolean noprune) {
        return imageHandler.removeImage(image, force, noprune);
    }

    @Override
    public void imageTag(DockerImageName original, DockerImageName newTag) {
        imageHandler.imageTag(original, newTag);
    }

    @Override
    public List<ImageSearchInfo> searchImage(String term) {
        return imageHandler.searchImage(term);
    }

    @Override
    public ImageInspect inspectImage(DockerImageName imageName) {
        return imageHandler.inspectImage(imageName);
    }

    @Override
    public List<ImageHistoryInfo> imageHistory(DockerImageName name) {
        return imageHandler.imageHistory(name);
    }

    @Override
    public InputStream buildImageFromArchive(BuildImageFromArchiveRequest request) {
        return imageHandler.buildImageFromArchive(request);
    }

    @Override
    public InputStream buildImageFromRemote(BuildImageFromRemoteRequest request) {
        return imageHandler.buildImageFromRemote(request);
    }

    @Override
    public InputStream getImageTar(DockerImageName imageName) {
        return imageHandler.getImageTar(imageName);
    }

    @Override
    public String importImageTar(InputStream input, boolean quiet) {
        return imageHandler.importImageTar(input, quiet);
    }


    @Override
    public String createNetwork(NetworkCreateRequest request) {
        return networkHandler.createNetwork(request);
    }

    @Override
    public List<Network> listNetworks() {
        return networkHandler.listNetworks();
    }

    @Override
    public List<Network> listNetworks(NetworkListParams params) {
        return networkHandler.listNetworks(params);
    }

    @Override
    public void removeNetwork(String networkName) {
        networkHandler.removeNetwork(networkName);
    }

    @Override
    public Network inspectNetwork(String networkName) {
        return networkHandler.inspectNetwork(networkName);
    }

    @Override
    public void connectContainerToNetwork(NetworkConnectRequest networkConnectRequest) {
        networkHandler.connectContainerToNetwork(networkConnectRequest);
    }

    @Override
    public void disconnectContainerFromNetwork(String container, String network, boolean force) {
        networkHandler.disconnectContainerFromNetwork(container, network, force);
    }

    @Override
    public Volume createVolume(VolumeCreateRequest request) {
        return volumeHandler.createVolume(request);
    }

    @Override
    public List<Volume> listVolumes() {
        return volumeHandler.listVolumes();
    }

    @Override
    public List<Volume> listVolumes(ListVolumeParams params) {
        return volumeHandler.listVolumes(params);
    }

    @Override
    public Volume inspectVolume(String volumeName) {
        return volumeHandler.inspectVolume(volumeName);
    }

    @Override
    public void removeVolume(String volumeName) {
        volumeHandler.removeVolume(volumeName);
    }

    @Override
    public String createExec(String containerId, ExecCreateRequest request) {
        return execHandler.createExec(containerId, request);
    }

    @Override
    public void startExec(String id, boolean tty) {
        execHandler.startExec(id, tty);
    }

    @Override
    public InputStream startExec(String id) {
        return execHandler.startExec(id);
    }

    @Override
    public ExecInfo inspectExec(String execId) {
        return execHandler.inspectExec(execId);
    }

    @Override
    public void resizeExec(String execId, int w, int h) {
        execHandler.resizeExec(execId, w, h);
    }

    @Override
    public String createContainer(ContainerCreationRequest request) {
        return containerHandler.createContainer(request);
    }

    @Override
    public List<Container> listContainers() {
        return containerHandler.listContainers();
    }

    @Override
    public List<Container> listContainers(ListContainerParams request) {
        return containerHandler.listContainers(request);
    }

    @Override
    public void kill(String id) {
        containerHandler.kill(id);
    }

    @Override
    public void kill(String id, String killSignal) {
        containerHandler.kill(id, killSignal);
    }

    @Override
    public ContainerInspect inspectContainer(String id, boolean size) {
        return containerHandler.inspectContainer(id, size);
    }

    @Override
    public void stop(String id) {
        containerHandler.stop(id, 10);
    }

    @Override
    public void stop(String id, int secondsUntilKill) {
        containerHandler.stop(id, secondsUntilKill);
    }

    @Override
    public void start(String id) {
        containerHandler.start(id);
    }

    @Override
    public List<String> logs(String id, DockerLogsParameters params) {
        return containerHandler.logs(id, params);
    }

    @Override
    public InputStream logsRawStream(String id, DockerLogsParameters params) {
        return containerHandler.logsRawStream(id, params);
    }

    @Override
    public InputStream logsStream(String id, DockerLogsParameters params) {
        return containerHandler.logsStream(id, params);
    }

    @Override
    public DockerLogsLineReader logsSpecial(String id, DockerLogsParameters params) {
        return containerHandler.logsSpecial(id, params);
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
    public ContainerProcesses top(String id) {
        return containerHandler.top(id, null);
    }

    @Override
    public ContainerProcesses top(String id, String args) {
        return containerHandler.top(id, args);
    }

    @Override
    public void waitForContainerStop(String id) {
        containerHandler.waitForContainerStop(id);
    }

    @Override
    public InputStream statsStream(String id) {
        return containerHandler.statsStream(id);
    }

    @Override
    public ContainerStats stats(String id) {
        return containerHandler.stats(id);
    }

    @Override
    public Warnings update(String id, ContainerUpdateRequest updateRequest) {
        return containerHandler.update(id, updateRequest);
    }

    @Override
    public void restart(String id) {
        containerHandler.restart(id, 0);
    }

    @Override
    public void restart(String id, int wait) {
        containerHandler.restart(id, wait);
    }

    @Override
    public void rename(String originalName, String newName) {
        containerHandler.rename(originalName, newName);
    }

    @Override
    public void resizeTty(String id, int w, int h) {
        containerHandler.resizeTty(id, w, h);
    }

    @Override
    public void remove(String id) {
        containerHandler.remove(id);
    }

    @Override
    public void remove(String id, boolean forceRemove, boolean removeVolume) {
        containerHandler.remove(id, forceRemove, removeVolume);
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
    public void fileSystemArchiveUpload(String id, String path, RequestStreamBody body) {
        containerHandler.fileSystemArchiveUpload(id, path, body);
    }

    @Override
    public String commitContainer(ContainerCommitRequest containerCommitRequest) {
        return containerHandler.commitContainer(containerCommitRequest);
    }

    @Override
    public List<ContainerFileSystemChange> containerFileSystemChanges(String id) {
        return containerHandler.containerFileSystemChanges(id);
    }

}
