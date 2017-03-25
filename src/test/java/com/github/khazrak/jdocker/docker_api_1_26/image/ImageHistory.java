package com.github.khazrak.jdocker.docker_api_1_26.image;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.ImageHistoryInfo;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.utils.DockerImageName;
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

public class ImageHistory {

    private static final Logger logger = LoggerFactory.getLogger(ImageHistory.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/image_history.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void history() {
        DockerImageName name = new DockerImageName("mongo");

        List<ImageHistoryInfo> history = client.imageHistory(name);

        for (ImageHistoryInfo info : history) {
            logger.info("Created by {} at {}, comments: {}", info.getCreatedBy(), info.getCreated(), info.getComment());
        }

        assertThat(history.get(0).getCreated()).isEqualTo(1488261885L);
        assertThat(history.size()).isEqualTo(19);

    }

}