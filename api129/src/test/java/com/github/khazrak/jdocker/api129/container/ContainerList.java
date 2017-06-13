package com.github.khazrak.jdocker.api129.container;

import com.github.khazrak.jdocker.abstraction.Container;
import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.ListContainerParams;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.api129.requests.ListContainerParams129;
import com.github.khazrak.jdocker.utils.DockerImageName;
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

public class ContainerList {

    private static final Logger logger = LoggerFactory.getLogger(ContainerList.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/container_list.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void list() {

        List<Container> containerList = client.listContainers();

        assertThat(containerList.size()).isEqualTo(3);

        DockerImageName mongo = new DockerImageName("mongo");

        Container one = containerList.get(0);
        Container two = containerList.get(1);
        Container three = containerList.get(2);

        logger.info(one.getState());

        assertThat(one.getImage()).isEqualTo(mongo);
        assertThat(two.getImage()).isEqualTo(mongo);
        assertThat(three.getImage()).isEqualTo(mongo);
    }

    @Test
    public void listAll() {
        ListContainerParams request = ListContainerParams129.builder().all(true).build();
        List<Container> containerList = client.listContainers(request);

        assertThat(containerList.size()).isEqualTo(4);

        DockerImageName mongo = new DockerImageName("mongo");

        Container one = containerList.get(0);
        Container two = containerList.get(1);
        Container three = containerList.get(2);

        assertThat(one.getImage()).isEqualTo(mongo);
        assertThat(two.getImage()).isEqualTo(mongo);
        assertThat(three.getImage()).isEqualTo(mongo);
    }

    @Test
    public void listLimit() {
        ListContainerParams request = ListContainerParams129.builder().limit(1).build();
        List<Container> containerList = client.listContainers(request);


        assertThat(containerList.size()).isEqualTo(1);

        DockerImageName busy = new DockerImageName("mongo:latest");

        Container one = containerList.get(0);

        assertThat(one.getImage()).isEqualTo(busy);
    }

    @Test
    public void listSize() {
        ListContainerParams request = ListContainerParams129.builder().size(true).build();
        List<Container> containerList = client.listContainers(request);

        assertThat(containerList.size()).isEqualTo(3);

        DockerImageName mongo = new DockerImageName("mongo");

        Container one = containerList.get(0);
        Container two = containerList.get(1);
        Container three = containerList.get(2);

        assertThat(one.getImage()).isEqualTo(mongo);
        assertThat(two.getImage()).isEqualTo(mongo);
        assertThat(three.getImage()).isEqualTo(mongo);
    }

    @Test
    public void listFilterBefore() {
        Filters filters = new Filters();
        filters.add("before", "dead_mongo");

        ListContainerParams request = ListContainerParams129.builder().filters(filters).build();
        List<Container> containerList = client.listContainers(request);

        assertThat(containerList.size()).isEqualTo(3);
        assertThat(containerList.get(0).getNames().get(0)).isEqualTo("/first_mongo");
    }

    @Test
    public void listFilterSince() {
        Filters filters = new Filters();
        filters.add("since", "last_mongo");

        ListContainerParams request = ListContainerParams129.builder().all(true).filters(filters).build();
        List<Container> containerList = client.listContainers(request);

        DockerImageName mongo = new DockerImageName("mongo");

        assertThat(containerList.size()).isEqualTo(2);
        assertThat(containerList.get(0).getNames().get(0)).isEqualTo("/dead_mongo");
        assertThat(containerList.get(1).getNames().get(0)).isEqualTo("/first_mongo");
        assertThat(containerList.get(0).getImage()).isEqualTo(mongo);
    }

}
