package com.github.khazrak.jdocker.api126.container;

import com.github.khazrak.jdocker.abstraction.Container;
import com.github.khazrak.jdocker.abstraction.ContainerInspect;
import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainerRename {

    private static final Logger logger = LoggerFactory.getLogger(ContainerRename.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/container_rename.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void rename() {
        List<Container> containers = client.listContainers();

        for (Container c : containers) {
            if (c.getNames().contains("/mongo_new")) {
                Assert.fail("mongo_new already exists");
            }
        }

        client.rename("mongo", "mongo_new");

        ContainerInspect inspect = client.inspectContainer("mongo_new", false);
        assertThat(inspect).isNotNull();

    }
}
