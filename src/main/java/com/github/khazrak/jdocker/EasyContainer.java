package com.github.khazrak.jdocker;


import com.github.khazrak.jdocker.model.api124.HostConfig;
import com.github.khazrak.jdocker.model.api124.HostPort;
import com.github.khazrak.jdocker.model.api124.NetworkSubConfig;
import com.github.khazrak.jdocker.model.api124.NetworkingConfig;
import com.github.khazrak.jdocker.model.api124.requests.ContainerCreationRequest;
import com.github.khazrak.jdocker.utils.DockerImageName;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EasyContainer {

    public EasyContainer(String imageName) {
        this(new DockerImageName(imageName));
    }

    public EasyContainer(DockerImageName imageName) {
        this.dockerImageName = imageName;
        requestBuilder = ContainerCreationRequest.builder();
        hostConfigBuilder = HostConfig.builder();
        networkingConfig = new NetworkingConfig();
        aliases = new ArrayList<>();
        links = new ArrayList<>();
        binds = new ArrayList<>();
        ports = new TreeMap<String, List<HostPort>>();
    }

    private ContainerCreationRequest.ContainerCreationRequestBuilder requestBuilder;
    private HostConfig.HostConfigBuilder hostConfigBuilder;
    private NetworkingConfig networkingConfig;

    private DockerImageName dockerImageName;
    private String containerName;
    private String command;
    private String net;
    private Map<String,List<HostPort>> ports;
    private List<String> aliases;
    private List<String> links;
    private List<String> binds;

    public EasyContainer addPublishPort(String hostIp, int hostPort, int containerPort) {

        HostPort port = HostPort.builder()
                .hostPort(Integer.toString(hostPort))
                .hostIp(hostIp)
                .build();
        List<HostPort> portList = new ArrayList<HostPort>();
        portList.add(port);
        ports.put(containerPort + "/tcp", portList);
        requestBuilder.exposedPort(Integer.toString(containerPort)+"/tcp", new Object());

        return this;
    }

    public EasyContainer addEnvVariable(String key, String value) {
        requestBuilder.environmentVariable(key + "=" + value);
        return this;
    }

    public EasyContainer cmd(String command) {
        this.command = command;
        return this;
    }

    public EasyContainer name(String containerName) {
        this.containerName = containerName;
        return this;
    }

    public EasyContainer net(String net) {
        this.net = net;
        return this;
    }

    public EasyContainer addAlias(String alias) {
        aliases.add(alias);
        return this;
    }

    public EasyContainer addLink(String link) {
        links.add(link);
        return this;
    }

    public EasyContainer addVolume(Path containerPath) {
        requestBuilder.volume(containerPath.toAbsolutePath().toString(), new Object());
        return this;
    }

    public EasyContainer addVolume(String volumeName, Path connectionPath) {
        binds.add(volumeName + ":" + connectionPath.toAbsolutePath().toString());
        return this;
    }

    public EasyContainer addHostVolume(Path hostPath, Path containerPath) {
        binds.add(hostPath.toAbsolutePath().toString() + ":" + containerPath.toAbsolutePath().toString());
        return this;
    }

    public ContainerCreationRequest.ContainerCreationRequestBuilder getRequestBuilder() {
        return this.requestBuilder;
    }

    public ContainerCreationRequest buildRequest() {

        requestBuilder.image(this.dockerImageName.toString());

        if(this.containerName != null) {
            requestBuilder.name(containerName);
        }

        if(net != null) {
            createNetConfig();
            requestBuilder.networkingConfig(networkingConfig);
        }
        if(ports.size() > 0) {
            createHostConfig();
            requestBuilder.hostConfig(hostConfigBuilder.build());
        }
        if(command != null) {
            createCommand();
        }

        return requestBuilder.build();
    }

    private void createCommand() {
        String [] cmds = command.split(" ");
        List<String> commands = Arrays.asList(cmds);
        requestBuilder.commands(commands);
    }

    private void createHostConfig() {
        hostConfigBuilder.portBindings(ports);
        hostConfigBuilder.binds(binds);
    }

    private void createNetConfig() {
        NetworkSubConfig networkSubConfig = NetworkSubConfig.builder()
                .aliases(aliases)
                .links(links)
                .build();

        networkingConfig.addEndpointConfig(net, networkSubConfig);
    }
}