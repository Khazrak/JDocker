package com.github.khazrak.jdocker;

import com.github.khazrak.jdocker.docker_api_1_24.container.ContainerTop;
import com.github.khazrak.jdocker.model.api124.Container;
import com.github.khazrak.jdocker.model.api124.DockerContainerInspect;
import com.github.khazrak.jdocker.model.api124.HostConfig;
import com.github.khazrak.jdocker.model.api124.HostPort;
import com.github.khazrak.jdocker.model.api124.requests.ContainerCreationRequest;

import com.github.khazrak.jdocker.model.api124.requests.NetworkCreateRequest;
import okhttp3.*;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

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

        //System.out.println(portBindings);

        String id = client.createContainer(containerCreationRequest);
        client.start(id);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Container> containers = client.listContainers();

        //System.out.println(containers);

        client.stop(id);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------");
        System.out.println(client.ps(true));

        client.remove(id);
    }

    @org.junit.Test
    public void testContainer() {
        try {
            NetworkCreateRequest networkCreateRequest = NetworkCreateRequest.builder()
                    .driver("bridge")
                    .name("test-network")
                    .checkDuplicate(true)
                    .build();
            client.createNetwork(networkCreateRequest);

            ContainerCreationRequest containerCreationRequest = ContainerCreationRequest.builder()
                    .name("test-container")
                    .image("ekino/wiremock:2.1.11")
                    .hostConfig(HostConfig.builder()
                            .networkMode("test-network")
                            .build())
                    .build();
            client.createContainer(containerCreationRequest);
            client.start("test-container");
            DockerContainerInspect dockerContainerInspect = client.inspectContainer("test-container", false);
            assertThat(dockerContainerInspect).isNotNull();
        } finally {
            try {
                client.stop("test-container");
            } finally {
                try {
                    client.remove("test-container");
                } finally {
                    client.removeNetwork("test-network");
                }
            }
        }
    }

}