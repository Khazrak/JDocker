package com.github.khazrak.jdocker.api126.network;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.IPAMConfig;
import com.github.khazrak.jdocker.abstraction.NetworkConnectRequest;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.api126.model.IPAMConfig126;
import com.github.khazrak.jdocker.api126.requests.NetworkConnectEndpointConfig126;
import com.github.khazrak.jdocker.api126.requests.NetworkConnectRequest126;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class NetworkConnectDisconnect {

    private static final Logger logger = LoggerFactory.getLogger(NetworkConnectDisconnect.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/network_connect.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void connect() {

        NetworkConnectRequest request = NetworkConnectRequest126.builder().networkName("test1").container("mongo").build();
        client.connectContainerToNetwork(request);

    }

    @Test
    public void connectWithEndpoint() {

        IPAMConfig ipamConfig = IPAMConfig126.builder().ipv4Address("172.18.1.1").build();
        NetworkConnectEndpointConfig126 endpointConfig = NetworkConnectEndpointConfig126.builder().config(ipamConfig).build();

        NetworkConnectRequest request = NetworkConnectRequest126.builder()
                .networkName("test1")
                .container("mongo2")
                .endpointConfig(endpointConfig)
                .build();

        client.connectContainerToNetwork(request);

    }

    @Test
    public void disconnect() {

        client.disconnectContainerFromNetwork("mongo3", "test1", false);

    }

}
