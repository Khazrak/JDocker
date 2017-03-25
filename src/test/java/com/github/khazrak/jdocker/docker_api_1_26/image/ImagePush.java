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

public class ImagePush {

    private static final Logger logger = LoggerFactory.getLogger(ImagePush.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/image_push.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void pushAuth() {
        DockerImageName imageToPush = new DockerImageName("localhost:5000/busybox:1.25");

        AuthConfig authConfig = AuthConfig126.builder().username("username").password("password").email("test@test.com").build();

        InputStream input = client.pushImage(imageToPush, authConfig);

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(lines.get(lines.size() - 1)).isEqualTo("{\"progressDetail\":{},\"aux\":{\"Tag\":\"1.25\",\"Digest\":\"sha256:cdbe636b45107ebde0a6a6d3f2a7ba4cd8dd9719937579bace5139377f98c72f\",\"Size\":527}}");
    }

}
