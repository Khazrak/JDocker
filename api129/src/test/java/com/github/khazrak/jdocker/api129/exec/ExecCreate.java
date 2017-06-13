package com.github.khazrak.jdocker.api129.exec;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.ExecCreateRequest;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.api129.requests.ExecCreateRequest129;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExecCreate {

    private static final Logger logger = LoggerFactory.getLogger(ExecCreate.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/exec_create.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void create() {
        List<String> commands = Arrays.asList("date");
        //List<String> commands = Arrays.asList("grep","-Rls","'.conf'","/");

        ExecCreateRequest request = ExecCreateRequest129.builder().attachStdOut(true).cmd(commands).build();

        String containerId = "mongo";
        String id = client.createExec(containerId, request);

        assertThat(id).isNotNull();
        assertThat(id).isEqualTo("2f1ec5af98ee7343e281eb771869850deab5e05fc8e19d108790bce579544e33");
    }

    @Test
    public void createWithTty() {
        List<String> commands = Arrays.asList("date");
        //List<String> commands = Arrays.asList("grep","-Rls","'.conf'","/");

        ExecCreateRequest request = ExecCreateRequest129.builder().attachStdOut(true).tty(true).cmd(commands).build();

        String containerId = "mongo";
        String id = client.createExec(containerId, request);

        assertThat(id).isNotNull();
        assertThat(id).isEqualTo("e0cc62a43e6dd10bf62be55f72b1cb7dc87d36fa4e93565b8b960c121c57a921");
    }
}
