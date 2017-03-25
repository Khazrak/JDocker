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

public class ContainerRemove {

    private static final Logger logger = LoggerFactory.getLogger(ContainerRemove.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/container_remove.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void removeForceAndVolume() {
        client.remove("mongo", true, true);
    }

    @Test
    public void removeForce() {
        client.remove("mongo2", true, false);
    }

    @Test
    public void removeWithVolume() {
        client.remove("mongo3", false, true);
    }

    @Test
    public void removeNormal() {
        client.remove("mongo4", false, false);
    }
}
