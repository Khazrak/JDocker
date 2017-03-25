package com.github.khazrak.jdocker.docker_api_1_26.container;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainerStats {

    private static final Logger logger = LoggerFactory.getLogger(ContainerStats.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/container_stats.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void stats() {
        com.github.khazrak.jdocker.abstraction.ContainerStats mongoStats = client.stats("mongo");

        long systemCpuUsage = 176407020000000L;

        assertThat(mongoStats.getCpuStats().getSystemCpuUsage()).isEqualTo(systemCpuUsage);
    }

    @Test
    public void statsStream() {
        InputStream input = client.statsStream("mongo2");
        int count = 0;
        int expected = 10;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(count).isEqualTo(expected);
    }

}
