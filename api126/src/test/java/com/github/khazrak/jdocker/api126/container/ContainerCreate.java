package com.github.khazrak.jdocker.api126.container;

import com.github.khazrak.jdocker.abstraction.ContainerCreationRequest;
import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.HealthCheck;
import com.github.khazrak.jdocker.abstraction.HostConfig;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
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

import static org.assertj.core.api.Assertions.assertThat;

public class ContainerCreate {

    private static final Logger logger = LoggerFactory.getLogger(ContainerCreate.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/container_create.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void containerCreate() {
        HostConfig hostConfig = HostConfig126.builder().build();

        HealthCheck healthCheck = HealthCheck126.builder().test(new String[]{"CMD-SHELL", "ls"}).interval(5).retries(2).build();

        ContainerCreationRequest test = ContainerCreationRequest126.builder()
                .name("test_container")
                .image(DockerImageName.of("mongo"))
                .healthCheck(healthCheck)
                .hostConfig(hostConfig)
                .build();

        String id = client.createContainer(test);

        assertThat(id).isEqualTo("78531d4df5797359494c2b36d428756256fa02fe91a4b61f1ebc1d3ee2f4402b");
    }
}
