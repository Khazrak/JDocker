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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        /*
        String unixSocketPath = "/var/run/docker.sock";
        UnixSocketFactory socketFactory = new UnixSocketFactory();
        HttpUrl url = socketFactory.urlForUnixSocketPath(unixSocketPath, "/v1.24/images/json");
        System.out.println("Working:");
        System.out.println(url);
        System.out.println("---------------------");
        System.out.println("Not Working");

        */

        /*
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
        */

        List<String> commands = Arrays.asList("grep","-Rls","'.conf'","/");

        ExecCreateRequest execCreateRequest = ExecCreateRequest.builder().cmd(commands).build();

        try {
            String id = client.createExec("mongo", execCreateRequest);
        }
        catch (Exception e) {

        }





        String unixSocketPath = "/var/run/docker.sock";
        UnixSocketFactory socketFactory = new UnixSocketFactory();
        HttpUrl url = socketFactory.urlForUnixSocketPath(unixSocketPath, "/v1.24/images/json");
        OkHttpClient client = new OkHttpClient.Builder()
                .socketFactory(socketFactory)
                .dns(socketFactory)
                .build();

        unixSocketPath = "/var/run/docker.sock";

        Map<String, Boolean> map = new TreeMap<>();

        //map.put("Detached")

        //url = socketFactory.urlForUnixSocketPath(unixSocketPath, "/v1.24/exec/"+id+"/json");
        url = socketFactory.urlForUnixSocketPath(unixSocketPath, "v1.24/containers/mongo/exec");

        String json = null;

        ObjectMapper mapper = new ObjectMapper();

        json = mapper.writeValueAsString(execCreateRequest);

        RequestBody body = RequestBody.create(JSON, json);


        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        System.out.println(request.url());

        Response response = client
                .newCall(request)
                .execute();

        ResponseBody responseBody = response.body();
        System.out.println(responseBody.string());

    }

    @Test
    public void bla() {

        Path p1 = Paths.get("/tmp/busybox-image2.tar.gz");
        Path p2 = Paths.get("/tmp/busybox-images5382536916064150339.tar");


        try(BufferedInputStream input1 = new BufferedInputStream(Files.newInputStream(p1, StandardOpenOption.READ));
            BufferedInputStream input2 = new BufferedInputStream(Files.newInputStream(p2, StandardOpenOption.READ))) {

            long d = 0;
            int data1, data2;
            while((data1 = input1.read()) != -1) {


                data2 = input2.read();

                if(data1 != data2) {
                    System.out.println("Wrong! "+d +" data1:" +data1 +", data2:"+data2);
                    break;
                }

                d++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
