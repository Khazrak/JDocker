package com.github.khazrak.jdocker.docker_api_1_26.volume;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.api126.requests.ListVolumeParams;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;

import static org.assertj.core.api.Assertions.assertThat;

public class VolumeRemove {

    private static final Logger logger = LoggerFactory.getLogger(VolumeRemove.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/volume_remove.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void volumeRemove() {

        ListVolumeParams volumeParams = ListVolumeParams.builder().name("vol").build();
        int size = client.listVolumes(volumeParams).size();
        assertThat(size).isEqualTo(1);
        client.removeVolume("vol");
        size = client.listVolumes().size();
        assertThat(size).isEqualTo(0);
    }
}
