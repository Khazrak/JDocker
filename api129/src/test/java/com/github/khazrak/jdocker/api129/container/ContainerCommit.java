package com.github.khazrak.jdocker.api129.container;

import com.github.khazrak.jdocker.abstraction.ContainerCommitRequest;
import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.api129.requests.ContainerCommit129;
import com.github.khazrak.jdocker.api129.requests.ContainerCommitRequest129;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainerCommit {

    private static final Logger logger = LoggerFactory.getLogger(ContainerCommit.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/container_commit.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void commitContainer() {

        Map<String, Object> exposedPorts = new TreeMap<>();
        exposedPorts.put("1337/tcp", new Object());

        com.github.khazrak.jdocker.abstraction.ContainerCommit commit = ContainerCommit129.builder()
                .exposedPorts(exposedPorts)
                .build();

        ContainerCommitRequest containerCommitRequest = ContainerCommitRequest129.builder()
                .containerCommit(commit)
                //.changes("RUN echo 'kasnke' >> /testar.txt \nRUN echo 'blabla' >> /bla.txt")
                .pause(true)
                .repo("test")
                .containerName("mongo")
                .tag("my_version")
                .build();

        String id = client.commitContainer(containerCommitRequest);

        assertThat(id).isEqualTo("sha256:e4e2aef613f084b98603c7aa4739a15ce8020c892df60e843fd1ddbbab790c53");
    }

    @Test
    public void commitContainerDockerFileCommands() {
        Map<String, Object> exposedPorts = new TreeMap<>();
        exposedPorts.put("1337/tcp", new Object());

        com.github.khazrak.jdocker.abstraction.ContainerCommit commit = ContainerCommit129.builder()
                .exposedPorts(exposedPorts)
                .build();

        ContainerCommitRequest containerCommitRequest = ContainerCommitRequest129.builder()
                .containerCommit(commit)
                .changes("CMD echo 'kasnke'")
                .pause(true)
                .repo("test/mongo")
                .containerName("mongo")
                .tag("my_version")
                .build();

        String id = client.commitContainer(containerCommitRequest);

        assertThat(id).isEqualTo("sha256:404c1735f5ad8c36c2d8e4357649f390c62b34bd5460654a83fbbad16af159c6");

    }

}
