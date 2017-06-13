package com.github.khazrak.jdocker.api129.volume;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.Volume;
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

public class VolumeInspect {

    private static final Logger logger = LoggerFactory.getLogger(VolumeInspect.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/volume_inspect.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void inspect() {
        Volume volume = client.inspectVolume("my_vol");

        assertThat(volume).isNotNull();
        assertThat(volume.getName()).isEqualTo("my_vol");
        assertThat(volume.getDriver()).isEqualTo("local");
        assertThat(volume.getMountPoint()).isEqualToIgnoringCase("/var/lib/docker/volumes/my_vol/_data");
    }
}
