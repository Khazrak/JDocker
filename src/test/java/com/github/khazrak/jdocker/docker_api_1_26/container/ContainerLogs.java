package com.github.khazrak.jdocker.docker_api_1_26.container;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.DockerLogsParameters;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.api126.requests.DockerLogsParameters126;
import com.github.khazrak.jdocker.utils.DockerLogsLineReader;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainerLogs {

    private static final Logger logger = LoggerFactory.getLogger(ContainerLogs.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/container_logs.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void logs() {
        int linesCount = 0;
        int expectedLineCount = 40;

        DockerLogsParameters params = DockerLogsParameters126.builder().stdout(true).build();

        List<String> logLines = client.logs("mongo", params);

        for (String s : logLines) {
            logger.info(s);
        }

        linesCount = logLines.size();

        assertThat(linesCount).isEqualTo(expectedLineCount);
    }

    @Test
    public void logsRawStream() {
        int linesCount = 0;
        int expectedLineCount = 40;

        DockerLogsParameters params = DockerLogsParameters126.builder().stdout(true).stderr(true).build();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.logsRawStream("mongo", params)))) {

            String line = "";
            while ((line = reader.readLine()) != null) {
                logger.info(line);
                linesCount++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(linesCount).isEqualTo(expectedLineCount);
    }

    @Test
    public void logsStream() {
        int linesCount = 0;
        int expectedLineCount = 40;

        DockerLogsParameters params = DockerLogsParameters126.builder().stdout(true).details(true).build();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.logsStream("mongo", params)))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                logger.info(line);
                linesCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(linesCount).isEqualTo(expectedLineCount);
    }

    @Test
    public void logsSpecial() {
        int linesCount = 0;
        int expectedLineCount = 40;

        DockerLogsParameters params = DockerLogsParameters126.builder().stdout(true).stderr(true).details(true).build();

        try (DockerLogsLineReader reader = client.logsSpecial("mongo", params)) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                logger.info(line);
                linesCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(linesCount).isEqualTo(expectedLineCount);
    }

    //TODO: Add more test for logging with only stderr in call and the other params
}
