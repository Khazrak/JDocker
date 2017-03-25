package com.github.khazrak.jdocker;

import com.github.khazrak.jdocker.abstraction.Container;
import com.github.khazrak.jdocker.abstraction.DockerClient;

import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.api126.easy.EasyContainer;
import com.github.khazrak.jdocker.api126.requests.ContainerCreationRequest126;
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
    private static Logger logger = LoggerFactory.getLogger(TestDockerNative.class);
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @org.junit.Test
    public void unix() throws IOException {
        DockerClient client = new DefaultDockerClient126();

        List<Container> containers = client.listContainers();

        for(Container c : containers) {
            System.out.println(c);
        }

        EasyContainer easyContainer = new EasyContainer("mongo");

        easyContainer.addPublishPort("0.0.0.0", 1337, 27017);

        easyContainer.name("my_easy_mongo");

        ContainerCreationRequest126 creationRequest = easyContainer.buildRequest();

        String container = client.createContainer(creationRequest);

        System.out.println(container);

    }

}