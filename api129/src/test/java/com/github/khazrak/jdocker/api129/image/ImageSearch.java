package com.github.khazrak.jdocker.api129.image;

import com.github.khazrak.jdocker.abstraction.DockerClient;
import com.github.khazrak.jdocker.abstraction.ImageSearchInfo;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
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

public class ImageSearch {

    private static final Logger logger = LoggerFactory.getLogger(ImageSearch.class);

    DockerClient client;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("1_29/image_search.json");

    @Before
    public void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", hoverflyRule.getProxyPort()));
        client = new DefaultDockerClient129("http://127.0.0.1:4243");
        client.setProxy(proxy);
    }

    @Test
    public void search() {
        List<ImageSearchInfo> searchResults = client.searchImage("grafana");

        boolean found = false;

        System.out.println(searchResults);

        for (ImageSearchInfo i : searchResults) {
            logger.debug(i.getName());
            if (i.getName().equals("grafana/grafana")) {
                found = true;
            }
        }

        assertThat(found).isTrue();

    }

}
