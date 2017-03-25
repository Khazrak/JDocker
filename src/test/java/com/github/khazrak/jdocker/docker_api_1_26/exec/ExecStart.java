package com.github.khazrak.jdocker.docker_api_1_26.exec;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.ExecCreateRequest;
import com.github.khazrak.jdocker.abstraction.ExecInfo;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.api126.requests.ExecCreateRequest126;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExecStart {

    private static final Logger logger = LoggerFactory.getLogger(ExecStart.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/exec_start.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243", proxy);
    }

    @Test
    public void startDetached() {
        client.startExec("49e007011a79784c42115e223119532fb2324ca8bea5800d08a27b27b212935e", true);
        ExecInfo execInfo = client.inspectExec("49e007011a79784c42115e223119532fb2324ca8bea5800d08a27b27b212935e");

        assertThat(execInfo.getExitCode()).isEqualTo(0);
    }

    @Test
    public void startAttached() {
        InputStream inputStream = client.startExec("54783e3c565d042fa4b10ef16b67753c6f7731a03d5b5f03298d0c54f13d3724");

        assertThat(inputStream).isNotNull();
        List<String> lines = new ArrayList<String>();


        try(BufferedReader reader= new BufferedReader((new InputStreamReader(inputStream)))) {
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(lines.size()).isEqualTo(1);
        assertThat(lines.get(0)).isEqualTo("Fri Mar 24 10:44:00 UTC 2017");
    }
}
