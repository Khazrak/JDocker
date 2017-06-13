package com.github.khazrak.jdocker.api129.volume;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.ListVolumeParams;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.api129.requests.ListVolumeParams129;
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
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/volume_remove.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void volumeRemove() {

        ListVolumeParams volumeParams = ListVolumeParams129.builder().name("my_vol").build();
        int size = client.listVolumes(volumeParams).size();
        assertThat(size).isEqualTo(1);
        client.removeVolume("my_vol");
        ListVolumeParams noDanglingParam = ListVolumeParams129.builder().dangling(false).build();
        size = client.listVolumes(noDanglingParam).size();
        assertThat(size).isEqualTo(0);
    }
}
