package com.github.khazrak.jdocker.docker_api_1_26.misc;


import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.SystemInfo;
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

public class Info {

    private static final Logger logger = LoggerFactory.getLogger(Info.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/info.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243", proxy);
    }

    @Test
    public void info() {
        SystemInfo info = client.info();
        assertThat(info.getOperatingSystem()).isEqualToIgnoringCase("Arch Linux");
    }



}
