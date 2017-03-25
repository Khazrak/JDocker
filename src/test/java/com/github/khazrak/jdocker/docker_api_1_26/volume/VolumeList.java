package com.github.khazrak.jdocker.docker_api_1_26.volume;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.Volume;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class VolumeList {

    private static final Logger logger = LoggerFactory.getLogger(VolumeList.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/volume_list.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void volumeList() {
        List<Volume> volumeList = client.listVolumes();
        assertThat(volumeList.size()).isEqualTo(10);
    }

    @Test
    public void listFiltersDanglingTrue() {
        ListVolumeParams params = ListVolumeParams.builder().dangling(true).build();
        List<Volume> volumeList = client.listVolumes(params);
        assertThat(volumeList.size()).isEqualTo(1);
    }

    @Test
    public void listFiltersDanglingFalse() {
        ListVolumeParams params = ListVolumeParams.builder().dangling(false).build();
        List<Volume> volumeList = client.listVolumes(params);
        assertThat(volumeList.size()).isEqualTo(9);
    }
}
