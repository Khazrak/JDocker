package com.github.khazrak.jdocker.api129.container;

import com.github.khazrak.jdocker.abstraction.ContainerFileSystemChange;
import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.api129.model.ContainerFileSystemChange129;
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
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/container_changes.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void changes() {
        ContainerFileSystemChange change1 = ContainerFileSystemChange129.builder().path("/tmp").kind(0).build();
        ContainerFileSystemChange change2 = ContainerFileSystemChange129.builder().path("/tmp/mongodb-27017.sock").kind(1).build();

        List<ContainerFileSystemChange> changes = client.containerFileSystemChanges("mongo");

        assertThat(changes.get(0)).isEqualTo(change1);
        assertThat(changes.get(1)).isEqualTo(change2);
    }
}
