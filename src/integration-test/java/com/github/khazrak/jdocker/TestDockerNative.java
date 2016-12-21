package com.github.khazrak.jdocker;

import com.github.khazrak.jdocker.docker_api_1_24.container.ContainerTop;
import com.github.khazrak.jdocker.model.api124.HostPort;
import com.github.khazrak.jdocker.model.api124.requests.ContainerCreationRequest;

import okhttp3.*;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class TestDockerNative {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(ContainerTop.class);
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Before
    public void setup() {
        client = new DefaultDockerClient();
    }

    @After
    public void tearDown() {
        client.close();
    }



    @org.junit.Test
    public void unix() throws IOException {

        EasyContainer container = new EasyContainer("mongo");
        container.net("my-net")
                .name("my-mongo")
                .addAlias("haha-mongo")
                .addPublishPort("0.0.0.0",1337,8080)
                .addVolume(Paths.get("/tmp"))
                .addVolume("my-vol", Paths.get("/my_volume"))
                .addHostVolume(Paths.get("/tmp/logs"), Paths.get("/var/log"))
                //.cmd("echo 'hello'")
                .addEnvVariable("key","value");

        ContainerCreationRequest containerCreationRequest = container.buildRequest();

        Map<String, List<HostPort>> portBindings = containerCreationRequest.getHostConfig().getPortBindings();

        System.out.println(portBindings);

        String id = client.createContainer(containerCreationRequest);
        client.start(id);

    }

}