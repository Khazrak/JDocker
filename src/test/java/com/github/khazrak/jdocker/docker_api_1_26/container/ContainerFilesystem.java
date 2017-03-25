package com.github.khazrak.jdocker.docker_api_1_26.container;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.FileSystemInfo;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.utils.RequestStreamBody;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainerFilesystem {

    private static final Logger logger = LoggerFactory.getLogger(ContainerFilesystem.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/container_filesystem.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }


    @Test
    public void info() {
        FileSystemInfo info = client.fileSystemInfo("mongo", "/root");

        assertThat(info.getName()).isEqualTo("root");
        assertThat(info.getSize()).isEqualTo(71);
    }

    @Test
    public void get() {
        InputStream input = client.fileSystemArchiveDownload("mongo", "/root");

        Path p = null;
        File f = null;
        try {
            f = File.createTempFile("myfile", ".tar");
            p = f.toPath();
        } catch (IOException e) {
            logger.error("Exception during creating temp file");
        }

        try (BufferedInputStream in = new BufferedInputStream(input); BufferedOutputStream output = new BufferedOutputStream(Files.newOutputStream(p, StandardOpenOption.CREATE))) {
            int data = -1;
            while ((data = in.read()) != -1) {
                output.write(data);
            }

        } catch (IOException e) {
            logger.error("Excpetion during download of tar", e);
        }

        long size = -1;
        try {
            size = Files.size(p);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            f.delete();
        }

        assertThat(size).isEqualTo(4096L);
    }

    @Test
    public void put() {
        InputStream input = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Path filePath = Paths.get(classLoader.getResource("upload.tar").toURI());
            input = Files.newInputStream(filePath, StandardOpenOption.READ);
        } catch (URISyntaxException e) {
            logger.error("Exception due to URI", e);
        } catch (IOException e) {
            logger.error("Exception due to IO", e);
        }

        RequestStreamBody body = new RequestStreamBody(input);

        client.fileSystemArchiveUpload("mongo", "/root", body);
    }

}
