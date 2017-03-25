package com.github.khazrak.jdocker.docker_api_1_26.network;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.Network;
import com.github.khazrak.jdocker.abstraction.NetworkListParams;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.api126.requests.NetworkListParams126;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NetworkRemove {

    private static final Logger logger = LoggerFactory.getLogger(NetworkRemove.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/network_remove.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void networkRemove() {
        NetworkListParams params = NetworkListParams126.builder().build();
        List<Network> networksList = client.listNetworks(params);
        assertThat(networksList.size()).isEqualTo(4);
        client.removeNetwork("test1");
        networksList = client.listNetworks();
        assertThat(networksList.size()).isEqualTo(3);
    }
}
