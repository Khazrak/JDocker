package com.github.khazrak.jdocker.docker_api_1_26.volume;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.Volume;
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

public class VolumeInspect {

    private static final Logger logger = LoggerFactory.getLogger(VolumeInspect.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/volume_inspect.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void inspect() {
        Volume volume = client.inspectVolume("vol2");

        assertThat(volume).isNotNull();
        assertThat(volume.getName()).isEqualTo("vol2");
        assertThat(volume.getDriver()).isEqualTo("local");
        assertThat(volume.getMountPoint()).isEqualToIgnoringCase("/var/lib/docker/volumes/vol2/_data");
    }
}
