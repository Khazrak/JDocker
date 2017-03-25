package com.github.khazrak.jdocker.docker_api_1_26.container;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainerRestart {

    private static final Logger logger = LoggerFactory.getLogger(ContainerRestart.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/container_restart.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void restart() {
        com.github.khazrak.jdocker.abstraction.ContainerInspect inspect = client.inspectContainer("mongo", true);
        assertThat(inspect.getState().isRestarting()).isFalse();
        client.restart("mongo");
        inspect = client.inspectContainer("mongo", false);
        //assertThat(inspect.getState().isRestarting()).isTrue();
    }

    @Test
    public void restartWait() {
        com.github.khazrak.jdocker.abstraction.ContainerInspect inspect = client.inspectContainer("mongo2", true);
        assertThat(inspect.getState().isRestarting()).isFalse();
        client.restart("mongo2", 20);
        inspect = client.inspectContainer("mongo2", false);
        //assertThat(inspect.getState().isRestarting()).isTrue();
    }
}
