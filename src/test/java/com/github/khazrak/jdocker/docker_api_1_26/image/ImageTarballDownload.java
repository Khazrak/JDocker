package com.github.khazrak.jdocker.docker_api_1_26.image;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.utils.DockerImageName;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageTarballDownload {

    private static final Logger logger = LoggerFactory.getLogger(ImageTarballDownload.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/image_tarball_download.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243", proxy);
    }

    @Test
    public void downloadBusyBox() {
        DockerImageName imageName = new DockerImageName("busybox");

        InputStream input = client.getImageTar(imageName);

        Path p = null;
        File f = null;
        try {
            f = File.createTempFile("busybox-images",".tar");
            p = f.toPath();
        } catch (IOException e) {
            logger.error("Exception during creating temp file");
        }

        try(BufferedInputStream in = new BufferedInputStream(input); BufferedOutputStream writer = new BufferedOutputStream(Files.newOutputStream(p, StandardOpenOption.CREATE))) {
            int data = -1;
            while((data = in.read()) != -1) {
                writer.write(data);
            }
            writer.flush();
        } catch (IOException e) {
            logger.error("Excpetion during download of tar", e);
        }

        long size = -1;
        try {
            size = Files.size(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            f.delete();
        }

        assertThat(size).isEqualTo(1321472L);
    }
}
