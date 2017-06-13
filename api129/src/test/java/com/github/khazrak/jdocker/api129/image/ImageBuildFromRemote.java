package com.github.khazrak.jdocker.api129.image;

import com.github.khazrak.jdocker.abstraction.AuthConfig;
import com.github.khazrak.jdocker.abstraction.BuildImageFromRemoteRequest;
import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.api129.model.AuthConfig129;
import com.github.khazrak.jdocker.api129.requests.BuildImageFromRemoteRequest129;
import com.github.khazrak.jdocker.utils.DockerImageName;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageBuildFromRemote {

    private static final Logger logger = LoggerFactory.getLogger(ImageBuildFromRemote.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/image_build_from_remote.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void build() {
        DockerImageName name = new DockerImageName("busy-echo");

        AuthConfig authConfig = AuthConfig129.builder().username("username").password("password").build();

        BuildImageFromRemoteRequest request = BuildImageFromRemoteRequest129.builder()
                .authConfig("localhost:5000", authConfig)
                .remoteUrl("https://github.com/SylvainLasnier/echo.git")
                .tag(name)
                .removeIntermediateContainers(true)
                .label("LICENSE", "GPL")
                .pull(true)
                .build();

        InputStream inputStream = client.buildImageFromRemote(request);

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                logger.info(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(lines.get(lines.size() - 1)).contains("{\"stream\":\"Successfully tagged busy-echo:latest");
    }


}
