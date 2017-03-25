package com.github.khazrak.jdocker.docker_api_1_26.image;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.ImageInfo;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.utils.DockerImageName;
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

public class ImageTag {

    private static final Logger logger = LoggerFactory.getLogger(ImageTag.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_26/image_tag.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient126("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void tag() {
        DockerImageName original = new DockerImageName("mongo");
        DockerImageName new_tag = new DockerImageName("mongo:mytag");

        boolean tagFound = false;

        List<ImageInfo> images = client.listImages(false);

        for (ImageInfo i : images) {
            if (searchForTag(i, "mongo:mytag")) {
                tagFound = true;
                break;
            }
        }

        assertThat(tagFound).isFalse();

        client.imageTag(original, new_tag);
        images = client.listImages(true);

        tagFound = false;

        for (ImageInfo i : images) {
            if (searchForTag(i, "mongo:mytag")) {
                tagFound = true;
                break;
            }
        }

        assertThat(tagFound).isTrue();
    }

    private boolean searchForTag(ImageInfo i, String tag) {

        if (i.getRepoTags() == null) return false;

        for (String s : i.getRepoTags()) {
            logger.debug(s);
            if (s.equals(tag)) {
                return true;
            }
        }

        return false;
    }

}
