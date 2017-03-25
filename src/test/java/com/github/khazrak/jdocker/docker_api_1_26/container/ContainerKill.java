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

public class ContainerKill {

    private static final Logger logger = LoggerFactory.getLogger(ContainerKill.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/container_kill.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243", proxy);
    }

    @Test
    public void kill() {
        com.github.khazrak.jdocker.abstraction.ContainerInspect inspect = client.inspectContainer("first_mongo", true);
        assertThat(inspect.getState().isRunning()).isTrue();
        client.kill("first_mongo");
        inspect = client.inspectContainer("first_mongo", false);
        assertThat(inspect.getState().isRunning()).isFalse();

    }

    @Test
    public void killSignal() {
        com.github.khazrak.jdocker.abstraction.ContainerInspect inspect = client.inspectContainer("last_mongo", true);
        assertThat(inspect.getState().isRunning()).isTrue();
        client.kill("last_mongo","SIGKILL");
        inspect = client.inspectContainer("last_mongo", false);
        assertThat(inspect.getState().isRunning()).isFalse();
    }
}
