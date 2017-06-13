package com.github.khazrak.jdocker.api129.volume;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.ListVolumeParams;
import com.github.khazrak.jdocker.abstraction.Volume;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class VolumeList {

    private static final Logger logger = LoggerFactory.getLogger(VolumeList.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/volume_list.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void volumeList() {
        List<Volume> volumeList = client.listVolumes();
        assertThat(volumeList.size()).isEqualTo(25);
    }

    @Test
    public void listFiltersDanglingTrue() {
        ListVolumeParams params = ListVolumeParams129.builder().dangling(true).build();
        List<Volume> volumeList = client.listVolumes(params);
        assertThat(volumeList.size()).isEqualTo(24);
    }

    @Test
    public void listFiltersDanglingFalse() {
        ListVolumeParams params = ListVolumeParams129.builder().dangling(false).build();
        List<Volume> volumeList = client.listVolumes(params);
        assertThat(volumeList.size()).isEqualTo(1);
    }
}
