package com.github.khazrak.jdocker.api129.easy;


import com.github.khazrak.jdocker.abstraction.HostPort;
import com.github.khazrak.jdocker.api129.model.HostConfig129;
import com.github.khazrak.jdocker.api129.model.HostPort129;
import com.github.khazrak.jdocker.api129.model.NetworkSubConfig129;
import com.github.khazrak.jdocker.api129.model.NetworkingConfig129;
import com.github.khazrak.jdocker.api129.requests.ContainerCreationRequest129;
import com.github.khazrak.jdocker.utils.DockerImageName;

import java.nio.file.Path;
import java.util.*;

public class EasyContainer129 {

    public EasyContainer129(String imageName) {
        this(new DockerImageName(imageName));
    }

    public EasyContainer129(DockerImageName imageName) {
        this.dockerImageName = imageName;
        requestBuilder = ContainerCreationRequest129.builder();
        hostConfigBuilder = HostConfig129.builder();
        networkingConfig = new NetworkingConfig129();
        aliases = new ArrayList<>();
        links = new ArrayList<>();
        binds = new ArrayList<>();
        ports = new TreeMap<>();
    }

    private ContainerCreationRequest129.ContainerCreationRequest129Builder requestBuilder;
    private HostConfig129.HostConfig129Builder hostConfigBuilder;
    private NetworkingConfig129 networkingConfig;

    private DockerImageName dockerImageName;
    private String containerName;
    private String command;
    private String net;
    private Map<String, List<HostPort>> ports;
    private List<String> aliases;
    private List<String> links;
    private List<String> binds;

    public EasyContainer129 addPublishPort(String hostIp, int hostPort, int containerPort) {

        HostPort129 port = HostPort129.builder()
                .hostPort(Integer.toString(hostPort))
                .hostIp(hostIp)
                .build();
        List<HostPort> portList = new ArrayList<>();
        portList.add(port);
        ports.put(containerPort + "/tcp", portList);
        requestBuilder.exposedPort(Integer.toString(containerPort) + "/tcp", new Object());

        return this;
    }

    public EasyContainer129 addEnvVariable(String key, String value) {
        requestBuilder.environmentVariable(key + "=" + value);
        return this;
    }

    public EasyContainer129 cmd(String command) {
        this.command = command;
        return this;
    }

    public EasyContainer129 name(String containerName) {
        this.containerName = containerName;
        return this;
    }

    public EasyContainer129 net(String net) {
        this.net = net;
        return this;
    }

    public EasyContainer129 addAlias(String alias) {
        aliases.add(alias);
        return this;
    }

    public EasyContainer129 addLink(String link) {
        links.add(link);
        return this;
    }

    public EasyContainer129 addVolume(Path containerPath) {
        requestBuilder.volume(containerPath.toAbsolutePath().toString(), new Object());
        return this;
    }

    public EasyContainer129 addVolume(String volumeName, Path connectionPath) {
        binds.add(volumeName + ":" + connectionPath.toAbsolutePath().toString());
        return this;
    }

    public EasyContainer129 addHostVolume(Path hostPath, Path containerPath) {
        binds.add(hostPath.toAbsolutePath().toString() + ":" + containerPath.toAbsolutePath().toString());
        return this;
    }

    public ContainerCreationRequest129.ContainerCreationRequest129Builder getRequestBuilder() {
        return this.requestBuilder;
    }

    public ContainerCreationRequest129 buildRequest() {

        requestBuilder.image(this.dockerImageName);

        if (this.containerName != null) {
            requestBuilder.name(containerName);
        }

        if (net != null) {
            createNetConfig();
            requestBuilder.networkingConfig(networkingConfig);
        }
        if (ports.size() > 0) {
            createHostConfig();
            requestBuilder.hostConfig(hostConfigBuilder.build());
        }
        if (command != null) {
            createCommand();
        }

        return requestBuilder.build();
    }

    private void createCommand() {
        String[] cmds = command.split(" ");
        List<String> commands = Arrays.asList(cmds);
        requestBuilder.commands(commands);
    }

    private void createHostConfig() {
        hostConfigBuilder.portBindings(ports);
        hostConfigBuilder.binds(binds);
    }

    private void createNetConfig() {
        NetworkSubConfig129 networkSubConfig = NetworkSubConfig129.builder()
                .aliases(aliases)
                .links(links)
                .build();

        networkingConfig.addEndpointConfig(net, networkSubConfig);
    }
}