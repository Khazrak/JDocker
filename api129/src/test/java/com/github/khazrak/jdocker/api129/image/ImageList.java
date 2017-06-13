package com.github.khazrak.jdocker.api129.image;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.ImageInfo;
import com.github.khazrak.jdocker.abstraction.ListImagesParams;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.api129.model.ListImagesParams129;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageList {

    private static final Logger logger = LoggerFactory.getLogger(ImageList.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/image_list.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void list() {
        List<ImageInfo> images = client.listImages(false);

        assertThat(images.size()).isEqualTo(6);

        assertThat(images.get(0).getRepoTags().get(0)).isEqualTo("mongo:hej");

    }

    @Test
    public void listDangling() {
        ListImagesParams params = ListImagesParams129.builder().dangling(true).build();

        List<ImageInfo> imageInfos = client.listImages(params);

        assertThat(imageInfos.size()).isEqualTo(1);
    }

    @Test
    public void listBefore() {
        ListImagesParams params = ListImagesParams129.builder().before("mongo").build();

        List<ImageInfo> imageInfos = client.listImages(params);

        assertThat(imageInfos.size()).isEqualTo(3);
    }

    @Test
    public void listRepoTags() {


        List<ImageInfo> imageInfos = client.listImages();
        assertThat(imageInfos.get(0).getRepoTags()).isNotNull();
    }

}
