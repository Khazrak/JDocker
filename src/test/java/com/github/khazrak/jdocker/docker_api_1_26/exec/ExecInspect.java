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

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExecInspect {

    private static final Logger logger = LoggerFactory.getLogger(ExecInspect.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/exec_inspect.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243", proxy);
    }

    @Test
    public void inspect() {
        ExecInfo exec = client.inspectExec("49e007011a79784c42115e223119532fb2324ca8bea5800d08a27b27b212935e");

        assertThat(exec).isNotNull();
        assertThat(exec.isCanRemove()).isEqualTo(false);
        assertThat(exec.getProcessConfig().getEntryPoint()).isEqualTo("date");
    }
}
