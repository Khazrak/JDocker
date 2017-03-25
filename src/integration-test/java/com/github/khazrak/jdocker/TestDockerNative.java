package com.github.khazrak.jdocker;

import com.github.khazrak.jdocker.abstraction.DockerClient;

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

    }

}