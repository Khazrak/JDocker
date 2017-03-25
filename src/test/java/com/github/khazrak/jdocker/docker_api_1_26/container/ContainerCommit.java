package com.github.khazrak.jdocker.docker_api_1_26.container;

import com.github.khazrak.jdocker.abstraction.ContainerCommitRequest;
import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.api126.requests.ContainerCommit126;
import com.github.khazrak.jdocker.api126.requests.ContainerCommitRequest126;
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
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/container_commit.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void commitContainer() {

        Map<String, Object> exposedPorts = new TreeMap<>();
        exposedPorts.put("1337/tcp", new Object());

        com.github.khazrak.jdocker.abstraction.ContainerCommit commit = ContainerCommit126.builder()
                .exposedPorts(exposedPorts)
                .build();

        ContainerCommitRequest containerCommitRequest = ContainerCommitRequest126.builder()
                .containerCommit(commit)
                //.changes("RUN echo 'kasnke' >> /testar.txt \nRUN echo 'blabla' >> /bla.txt")
                .pause(true)
                .repo("test")
                .containerName("mongo")
                .tag("my_version")
                .build();

        String id = client.commitContainer(containerCommitRequest);

        assertThat(id).isEqualTo("sha256:b6311d2f212a73ea82db79f44bd21f4b4ede7ceac7054587392580d31e68155a");
    }

    @Test
    public void commitContainerDockerFileCommands() {
        Map<String, Object> exposedPorts = new TreeMap<>();
        exposedPorts.put("1337/tcp", new Object());

        com.github.khazrak.jdocker.abstraction.ContainerCommit commit = ContainerCommit126.builder()
                .exposedPorts(exposedPorts)
                .build();

        ContainerCommitRequest containerCommitRequest = ContainerCommitRequest126.builder()
                .containerCommit(commit)
                .changes("CMD echo 'kasnke'")
                .pause(true)
                .repo("test/mongo")
                .containerName("mongo")
                .tag("my_version")
                .build();

        String id = client.commitContainer(containerCommitRequest);

        assertThat(id).isEqualTo("sha256:13c68b5d76e11623d82d0e0c4cc0e44c21b3ce90f28d8f10f26d6d3ff59f1488");

    }

}
