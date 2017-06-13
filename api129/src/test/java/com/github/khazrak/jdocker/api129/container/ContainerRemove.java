package com.github.khazrak.jdocker.api129.container;

import com.github.khazrak.jdocker.abstraction.Container;
import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.ListContainerParams;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.api129.requests.ListContainerParams129;
import com.github.khazrak.jdocker.utils.Filters;
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

public class ContainerRemove {

    private static final Logger logger = LoggerFactory.getLogger(ContainerRemove.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/container_remove.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void removeForceAndVolume() {
        Filters f = new Filters();
        f.add("name","mongo1");

        ListContainerParams params = ListContainerParams129.builder().filters(f).build();
        List<Container> containers = client.listContainers(params);

        assertThat(containers.size()).isEqualTo(1);

        client.remove("mongo1", true, true);

        params = ListContainerParams129.builder().all(true).limit(3).filters(f).build();
        containers = client.listContainers(params);
        assertThat(containers.size()).isEqualTo(0);
    }

    @Test
    public void removeForce() {
        Filters f = new Filters();
        f.add("name","mongo2");

        ListContainerParams params = ListContainerParams129.builder().filters(f).build();
        List<Container> containers = client.listContainers(params);

        assertThat(containers.size()).isEqualTo(1);

        client.remove("mongo2", true, false);

        params = ListContainerParams129.builder().all(true).limit(3).filters(f).build();
        containers = client.listContainers(params);
        assertThat(containers.size()).isEqualTo(0);
    }

    @Test
    public void removeWithVolume() {
        Filters f = new Filters();
        f.add("name","mongo3");

        ListContainerParams params = ListContainerParams129.builder().all(true).filters(f).build();
        List<Container> containers = client.listContainers(params);

        assertThat(containers.size()).isEqualTo(1);

        client.remove("mongo3", false, true);

        params = ListContainerParams129.builder().all(true).limit(3).filters(f).build();
        containers = client.listContainers(params);
        assertThat(containers.size()).isEqualTo(0);
    }

    @Test
    public void removeNormal() {
        Filters f = new Filters();
        f.add("name","mongo4");

        ListContainerParams params = ListContainerParams129.builder().all(true).filters(f).build();
        List<Container> containers = client.listContainers(params);

        assertThat(containers.size()).isEqualTo(1);

        client.remove("mongo4", false, false);

        params = ListContainerParams129.builder().all(true).limit(3).filters(f).build();
        containers = client.listContainers(params);
        assertThat(containers.size()).isEqualTo(0);
    }
}
