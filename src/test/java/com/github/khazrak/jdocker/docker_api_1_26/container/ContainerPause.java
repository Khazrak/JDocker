package com.github.khazrak.jdocker.docker_api_1_26.container;

import com.github.khazrak.jdocker.abstraction.*;
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

public class ContainerPause {

    private static final Logger logger = LoggerFactory.getLogger(ContainerPause.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/container_pause.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243", proxy);
    }

    @Test
    public void pause() {
        com.github.khazrak.jdocker.abstraction.ContainerInspect inspect = client.inspectContainer("mongo", true);
        assertThat(inspect.getState().isPaused()).isFalse();
        client.pause("mongo");
        inspect = client.inspectContainer("mongo", false);
        assertThat(inspect.getState().isPaused()).isTrue();
    }

    @Test
    public void unPause() {
        com.github.khazrak.jdocker.abstraction.ContainerInspect inspect = client.inspectContainer("mongo_paused", true);
        assertThat(inspect.getState().isPaused()).isTrue();
        client.unpause("mongo_paused");
        inspect = client.inspectContainer("mongo_paused", false);
        assertThat(inspect.getState().isPaused()).isFalse();
    }
}
