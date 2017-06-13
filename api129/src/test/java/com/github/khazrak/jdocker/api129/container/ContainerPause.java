package com.github.khazrak.jdocker.api129.container;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
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
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/container_pause.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
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
