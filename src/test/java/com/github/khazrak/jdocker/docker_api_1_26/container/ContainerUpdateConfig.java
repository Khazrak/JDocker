package com.github.khazrak.jdocker.docker_api_1_26.container;

import com.github.khazrak.jdocker.abstraction.ContainerUpdateRequest;
import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.Warnings;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.api126.requests.ContainerUpdateRequest126;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainerUpdateConfig {

    private static final Logger logger = LoggerFactory.getLogger(ContainerUpdateConfig.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/container_update_config.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void update() {
        ContainerUpdateRequest update = ContainerUpdateRequest126.builder().cpuShares(2).build();

        Warnings warnings = client.update("mongo", update);

        assertThat(warnings.getWarnings()).isNull();
    }

    @Test
    public void updateWithWarnings() {
        ContainerUpdateRequest update = ContainerUpdateRequest126.builder().cpuShares(3).build();

        Warnings warnings = client.update("mongo2", update);

        assertThat(warnings.getWarnings()).isNotNull();
        assertThat(warnings.size()).isEqualTo(1);
        assertThat(warnings.getWarning(0)).isEqualTo("This is a test warning");

    }

}
