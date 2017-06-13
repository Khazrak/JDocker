package com.github.khazrak.jdocker.api129.image;

import com.github.khazrak.jdocker.abstraction.AuthConfig;
import com.github.khazrak.jdocker.abstraction.BuildImageFromArchiveRequest;
import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.api129.model.AuthConfig129;
import com.github.khazrak.jdocker.api129.requests.BuildImageFromArchiveRequest129;
import com.github.khazrak.jdocker.utils.DockerImageName;
import com.github.khazrak.jdocker.utils.RequestStreamBody;
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
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageBuildFromArchive {

    private static final Logger logger = LoggerFactory.getLogger(ImageBuildFromArchive.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/image_build_from_archive.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void build() {
        DockerImageName name = new DockerImageName("test_buntu:1.0");

        InputStream input = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Path filePath = Paths.get(classLoader.getResource("to_build.tar.gz").toURI());
            input = Files.newInputStream(filePath, StandardOpenOption.READ);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestStreamBody body = new RequestStreamBody(input);

        AuthConfig authConfig = AuthConfig129.builder().username("username").password("password").build();

        BuildImageFromArchiveRequest request = BuildImageFromArchiveRequest129.builder()
                .authConfig("localhost:5000", authConfig)
                .tag(name)
                .body(body)
                .removeIntermediateContainers(true)
                .pull(true)
                .build();

        InputStream inputStream = client.buildImageFromArchive(request);

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

        assertThat(lines.get(lines.size() - 1)).contains("{\"stream\":\"Successfully tagged test_buntu:1.0");
    }

    @Test
    public void buildWithRemoteRegistry() {

        DockerImageName name = new DockerImageName("test_buntu:2.0");

        InputStream input = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Path filePath = Paths.get(classLoader.getResource("to_build2.tar.gz").toURI());
            input = Files.newInputStream(filePath, StandardOpenOption.READ);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestStreamBody body = new RequestStreamBody(input);

        AuthConfig authConfig = AuthConfig129.builder().username("username").password("password").build();

        BuildImageFromArchiveRequest request = BuildImageFromArchiveRequest129.builder()
                .authConfig("localhost:5000", authConfig)
                .tag(name)
                .body(body)
                .removeIntermediateContainers(true)
                .pull(true)
                .build();

        InputStream inputStream = client.buildImageFromArchive(request);

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

        assertThat(lines.get(lines.size() - 1)).contains("{\"stream\":\"Successfully tagged test_buntu:2.0\\");

    }

}
