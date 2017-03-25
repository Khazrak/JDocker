package com.github.khazrak.jdocker.docker_api_1_26.network;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.NetworkCreateRequest;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.api126.requests.NetworkCreateRequest126;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;

import static org.assertj.core.api.Assertions.assertThat;

public class NetworkCreate {

    private static final Logger logger = LoggerFactory.getLogger(NetworkCreate.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/network_create.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void networkCreate() {
        NetworkCreateRequest request = NetworkCreateRequest126.builder().name("test1").build();

        String id = client.createNetwork(request);

        assertThat(id).isEqualTo("5ce416bdf3fffb566b08b5b18268552b8c57df2f78f25eaaeeedd798b1377296");
    }
}
