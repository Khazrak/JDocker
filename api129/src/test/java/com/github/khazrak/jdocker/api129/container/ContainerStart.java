package com.github.khazrak.jdocker.api129.container;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.exception.DockerClientException;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class ContainerStart {

    private static final Logger logger = LoggerFactory.getLogger(ContainerStart.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/container_start.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);

    }

    @Test
    public void start() {
        client.start("mongo");
    }

    @Test(expected = DockerClientException.class)
    public void startNoneExisting() {

        client.start("none_existing");
    }

    @Test
    public void startAlreadyStarted() {
        //Will log 304
        client.start("already_started");
    }
}
