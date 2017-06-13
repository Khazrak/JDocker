package com.github.khazrak.jdocker.api129.network;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.IPAMConfig;
import com.github.khazrak.jdocker.abstraction.NetworkConnectRequest;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.api129.model.IPAMConfig129;
import com.github.khazrak.jdocker.api129.requests.NetworkConnectEndpointConfig129;
import com.github.khazrak.jdocker.api129.requests.NetworkConnectRequest129;
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
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/network_connect.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void connect() {

        NetworkConnectRequest request = NetworkConnectRequest129.builder().networkName("test1").container("mongo").build();
        client.connectContainerToNetwork(request);

    }

    @Test
    public void connectWithEndpoint() {

        IPAMConfig ipamConfig = IPAMConfig129.builder().ipv4Address("171.19.1.5").build();
        NetworkConnectEndpointConfig129 endpointConfig = NetworkConnectEndpointConfig129.builder().config(ipamConfig).build();

        NetworkConnectRequest request = NetworkConnectRequest129.builder()
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
