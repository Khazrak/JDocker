package com.github.khazrak.jdocker.api129.network;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.Network;
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

public class NetworkInspect {

    private static final Logger logger = LoggerFactory.getLogger(NetworkInspect.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/network_inspect.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void inspect() {
        Network network = client.inspectNetwork("test1");
        assertThat(network).isNotNull();
        assertThat(network.getName()).isEqualTo("test1");
        assertThat(network.getIpam().getConfig().get(0).getSubnet()).isEqualToIgnoringCase("171.19.1.0/8");
    }
}
