package com.github.khazrak.jdocker.docker_api_1_26.exec;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.ExecCreateRequest;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.api126.requests.ExecCreateRequest126;
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
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/exec_create.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243", proxy);
    }

    @Test
    public void create() {
        List<String> commands = Arrays.asList("date");
        //List<String> commands = Arrays.asList("grep","-Rls","'.conf'","/");

        ExecCreateRequest request = ExecCreateRequest126.builder().attachStdOut(true).cmd(commands).build();

        String containerId = "mongo";
        String id = client.createExec(containerId, request);

        assertThat(id).isNotNull();
        assertThat(id).isEqualTo("d01746eb585d5f7b8b1124d24071c44cd96e2697dc8aeb20dc149f4d77c9054a");
    }

    @Test
    public void createWithTty() {
        List<String> commands = Arrays.asList("date");
        //List<String> commands = Arrays.asList("grep","-Rls","'.conf'","/");

        ExecCreateRequest request = ExecCreateRequest126.builder().attachStdOut(true).tty(true).cmd(commands).build();

        String containerId = "mongo";
        String id = client.createExec(containerId, request);

        assertThat(id).isNotNull();
        assertThat(id).isEqualTo("628c5a19347ead186224b3b0bdbbb0593b05e0479c72435f88d903a7b663b105");
    }
}
