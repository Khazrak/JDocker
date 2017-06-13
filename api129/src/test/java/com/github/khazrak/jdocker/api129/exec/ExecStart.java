package com.github.khazrak.jdocker.api129.exec;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.ExecInfo;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExecStart {

    private static final Logger logger = LoggerFactory.getLogger(ExecStart.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/exec_start.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void startDetached() {
        client.startExec("2f1ec5af98ee7343e281eb771869850deab5e05fc8e19d108790bce579544e33", true);
        ExecInfo execInfo = client.inspectExec("2f1ec5af98ee7343e281eb771869850deab5e05fc8e19d108790bce579544e33");

        assertThat(execInfo.getExitCode()).isEqualTo(0);
    }

    @Test
    public void startAttached() {
        InputStream inputStream = client.startExec("e0cc62a43e6dd10bf62be55f72b1cb7dc87d36fa4e93565b8b960c121c57a921");

        assertThat(inputStream).isNotNull();
        List<String> lines = new ArrayList<String>();


        try (BufferedReader reader = new BufferedReader((new InputStreamReader(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(lines.size()).isEqualTo(1);
        assertThat(lines.get(0)).isEqualTo("Tue Jun 13 14:36:44 UTC 2017");
    }
}
