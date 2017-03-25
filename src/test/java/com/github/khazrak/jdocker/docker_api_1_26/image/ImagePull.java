package com.github.khazrak.jdocker.docker_api_1_26.image;

import com.github.khazrak.jdocker.abstraction.AuthConfig;
import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.api126.model.AuthConfig126;
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

public class ImagePull {

    private static final Logger logger = LoggerFactory.getLogger(ImagePull.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/image_pull.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243", proxy);
    }

    @Test
    public void pull() {
        DockerImageName image = new DockerImageName("busybox");

        InputStream input = client.pullImage(image);

        List<String> lines = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
                logger.info(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(lines.get(lines.size()-1)).contains("{\"status\":\"Status: Downloaded newer image for busybox:latest\"}");

    }

    @Test
    public void pullAuth() {
        DockerImageName image = new DockerImageName("localhost:5000/busybox:1.25");
        AuthConfig authConfig = AuthConfig126.builder().username("username").password("password").email("test@test.se").build();
        InputStream input = client.pullImage(image, authConfig);

        List<String> lines = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
                logger.info(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(lines.get(lines.size()-1)).contains("{\"status\":\"Status: Downloaded newer image for localhost:5000/busybox:1.25\"}");
    }

    //@Test
    public void pullToken() {
        //TODO: private Registry with token
    }


}
