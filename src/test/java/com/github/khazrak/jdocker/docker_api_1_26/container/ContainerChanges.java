package com.github.khazrak.jdocker.docker_api_1_26.container;

import com.github.khazrak.jdocker.abstraction.*;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.api126.model.ContainerFileSystemChange126;
import com.github.khazrak.jdocker.api126.model.HealthCheck126;
import com.github.khazrak.jdocker.api126.model.HostConfig126;
import com.github.khazrak.jdocker.api126.requests.ContainerCreationRequest126;
import com.github.khazrak.jdocker.utils.DockerImageName;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainerChanges {

    private static final Logger logger = LoggerFactory.getLogger(ContainerChanges.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/container_changes.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243", proxy);
    }

    @Test
    public void changes() {
        ContainerFileSystemChange change1 = ContainerFileSystemChange126.builder().path("/tmp").kind(0).build();
        ContainerFileSystemChange change2 = ContainerFileSystemChange126.builder().path("/tmp/mongodb-27017.sock").kind(1).build();

        List<ContainerFileSystemChange> changes = client.containerFileSystemChanges("mongo");

        assertThat(changes.get(0)).isEqualTo(change1);
        assertThat(changes.get(1)).isEqualTo(change2);
    }
}
