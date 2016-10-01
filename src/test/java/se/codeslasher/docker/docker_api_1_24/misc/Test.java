package se.codeslasher.docker.docker_api_1_24.misc;

import okhttp3.*;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.DefaultDockerClient;
import se.codeslasher.docker.DockerClient;
import se.codeslasher.docker.docker_api_1_24.container.ContainerTop;
import se.codeslasher.docker.model.api124.ImageInfo;
import se.codeslasher.docker.model.api124.parameters.ListImagesParams;
import se.codeslasher.docker.unixsocket.UnixSocketFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by karl on 9/25/16.
 */
public class Test {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(ContainerTop.class);

    @Before
    public void setup() {
        client = new DefaultDockerClient();
    }

    @After
    public void tearDown() {
        client.close();
    }



    @org.junit.Test
    public void unix() throws IOException{
        String unixSocketPath = "/var/run/docker.sock";
        UnixSocketFactory socketFactory = new UnixSocketFactory();
        HttpUrl url = socketFactory.urlForUnixSocketPath(unixSocketPath, "/v1.24/images/json");
        System.out.println("Working:");
        System.out.println(url);
        System.out.println("---------------------");
        System.out.println("Not Working");


        ListImagesParams params = ListImagesParams.builder().since("ubuntu:14.04").build();

        List<ImageInfo> images = client.listImages(params);

        for(ImageInfo i : images) {
            System.out.println(i.getRepoTags().get(0));
       }

       DockerClient client2 = new DefaultDockerClient("http://127.0.0.1:4243");
        images = client2.listImages(params);

        for(ImageInfo i : images) {
            System.out.println(i.getRepoTags().get(0));
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .socketFactory(socketFactory)
                .dns(socketFactory)
                .build();

        unixSocketPath = "/var/run/docker.sock";

        Map<String, String> map = new TreeMap<>();

        map.put("all","false");

        url = socketFactory.urlForUnixSocketPath(unixSocketPath, "/v1.24/images/json",map);
        Request request = new Request.Builder()
                .url(url)
                .build();

        System.out.println(request.url());

        Response response = client
                .newCall(request)
                .execute();

        ResponseBody body = response.body();
        System.out.println(body.string());

    }

}
