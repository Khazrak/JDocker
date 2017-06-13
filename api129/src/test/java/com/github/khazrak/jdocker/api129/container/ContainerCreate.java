package com.github.khazrak.jdocker.api129.container;

import com.github.khazrak.jdocker.abstraction.ContainerCreationRequest;
import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.HealthCheck;
import com.github.khazrak.jdocker.abstraction.HostConfig;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.api129.model.HealthCheck129;
import com.github.khazrak.jdocker.api129.model.HostConfig129;
import com.github.khazrak.jdocker.api129.requests.ContainerCreationRequest129;
import com.github.khazrak.jdocker.utils.DockerImageName;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainerCreate {

    private static final Logger logger = LoggerFactory.getLogger(ContainerCreate.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/container_create.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void containerCreate() {
        HostConfig hostConfig = HostConfig129.builder().build();

        HealthCheck healthCheck = HealthCheck129.builder().test(new String[]{"CMD-SHELL", "ls"}).interval(5_000_000_000L).retries(2).build();

        ContainerCreationRequest test = ContainerCreationRequest129.builder()
                .name("test_container")
                .image(DockerImageName.of("mongo"))
                .healthCheck(healthCheck)
                .hostConfig(hostConfig)
                .build();

        String id = client.createContainer(test);

        assertThat(id).isEqualTo("0453d2fb39c2b34e95037bb61f66b22a10edff5edaa96e0835ace0626a1cf758");
    }
}
