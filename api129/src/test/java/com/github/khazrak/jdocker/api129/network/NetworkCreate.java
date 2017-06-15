package com.github.khazrak.jdocker.api129.network;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.NetworkCreateIpam;
import com.github.khazrak.jdocker.abstraction.NetworkCreateIpamConfig;
import com.github.khazrak.jdocker.abstraction.NetworkCreateRequest;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.api129.requests.NetworkCreateIpam129;
import com.github.khazrak.jdocker.api129.requests.NetworkCreateIpamConfig129;
import com.github.khazrak.jdocker.api129.requests.NetworkCreateRequest129;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class NetworkCreate {

    private static final Logger logger = LoggerFactory.getLogger(NetworkCreate.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/network_create.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void networkCreate() {
        NetworkCreateIpamConfig netIpamConf = NetworkCreateIpamConfig129.builder().subnet("171.19.1.0/8").build();
        NetworkCreateIpam netIpam = NetworkCreateIpam129.builder().config(Arrays.asList(netIpamConf)).build();
        NetworkCreateRequest request = NetworkCreateRequest129.builder().ipam(netIpam).name("test1").build();

        String id = client.createNetwork(request);

        assertThat(id).isEqualTo("d406c15cff8ed9ddd028317fc41459ce3a31482a2027874218f4fe9ffa56a81d");
    }
}
