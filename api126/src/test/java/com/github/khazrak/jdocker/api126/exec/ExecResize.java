package com.github.khazrak.jdocker.api126.exec;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.ExecInfo;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class ExecResize {

    private static final Logger logger = LoggerFactory.getLogger(ExecResize.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/exec_resize.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    //@Test //TODO: make a test that works
    public void resize() {
        String id = "cb59e8234276736663c3faad47892c1c3a20601e9d882e464d50782b4158d802";
        client.resizeExec(id, 78, 77);
        ExecInfo execInfo = client.inspectExec(id);
        logger.info(execInfo.toString());
    }
}
