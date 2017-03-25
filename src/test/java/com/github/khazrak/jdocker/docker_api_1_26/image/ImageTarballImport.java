package com.github.khazrak.jdocker.docker_api_1_26.image;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageTarballImport {

    private static final Logger logger = LoggerFactory.getLogger(ImageTarballImport.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/image_tarball_import.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243", proxy);
    }

    @Test
    public void importBusyBox() {
        InputStream input = null;
        Path filePath = null;
        String output = null;


        try {
            ClassLoader classLoader = getClass().getClassLoader();
            filePath = Paths.get(classLoader.getResource("1_26/busybox-image.tar").toURI());

            input = Files.newInputStream(filePath, StandardOpenOption.READ);
            output = client.importImageTar(input, false);
            logger.info(output);
        } catch (URISyntaxException e) {
            logger.error("Exception due to URI", e);
        } catch (IOException e) {
            logger.error("Exception due to IO", e);
        }




        assertThat(output).contains("{\"stream\":\"Loaded image: busybox:latest\\n\"}");
    }

}
