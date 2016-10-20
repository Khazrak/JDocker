package se.codeslasher.docker;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okio.BufferedSink;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.docker_api_1_24.container.ContainerTop;
import se.codeslasher.docker.model.api124.ImageInfo;
import se.codeslasher.docker.model.api124.parameters.ListImagesParams;
import se.codeslasher.docker.model.api124.requests.ExecCreateRequest;
import se.codeslasher.docker.unixsocket.UnixSocketFactory;
import se.codeslasher.docker.utils.DockerImageName;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.GeneralSecurityException;
import java.util.*;

/**
 * Created by karl on 9/27/16.
 */
public class TestDockerNative {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(ContainerTop.class);
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Before
    public void setup() {
        client = new DefaultDockerClient();
    }

    @After
    public void tearDown() {
        client.close();
    }



    @org.junit.Test
    public void unix() throws IOException {
        //System.out.println(client.version().getApiVersion());

        List<ImageInfo> imageInfos = client.listImages(false);
        System.out.println(imageInfos.size());

        //NetworkCreateRequest request = NetworkCreateRequest.builder().name("my-net").build();
        //System.out.println(client.createNetwork(request));

        /*

        DockerImageName busybox = new DockerImageName("busybox");

        InputStream input = client.pullImage(busybox);

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
        */



    }

}
