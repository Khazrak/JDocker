package com.github.khazrak.jdocker.api129.exec;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.ExecInfo;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
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
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/exec_resize.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    //@Test //TODO: make a test that works
    public void resize() {
        String id = "e9d319476cdc7466755b8765c87c2050d1c753be28935a12a481328277fed855";
        client.resizeExec(id, 78, 77);
        ExecInfo execInfo = client.inspectExec(id);
        logger.info(execInfo.toString());
    }
}
