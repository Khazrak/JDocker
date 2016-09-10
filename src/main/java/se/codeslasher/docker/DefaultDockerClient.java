package se.codeslasher.docker;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import se.codeslasher.docker.exception.DockerServerException;

import java.io.IOException;

/**
 * Created by karl on 9/5/16.
 */
public class DefaultDockerClient implements DockerClient {

    private OkHttpClient httpClient;

    private final String URL;

    public DefaultDockerClient() {
        httpClient = new OkHttpClient();
        URL = "http://127.0.0.1:4243";
    }

    public DefaultDockerClient(String host) {
        httpClient = new OkHttpClient();
        URL = host;
    }

    public void close()  {
        httpClient = null;
    }

    @Override
    public String createContainer(ContainerCreation spec) {
        String id = null;


        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        try {
            String json = mapper.writeValueAsString(spec);

            RequestBody body = RequestBody.create(ContainerCreation.JSON,json);

            Request request = new Request.Builder()
                    .url(URL+ContainerCreation.PATH)
                    .post(body)
                    .build();
            response = httpClient.newCall(request).execute();
            id = response.body().string();

            id = mapper.readTree(id).findValue("Id").asText();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;

    }

    @Override
    public void start(String id) {
        final String path = "/v1.24/containers/"+id+"/start";

        Response resonse;

        RequestBody body = RequestBody.create(ContainerCreation.JSON,"");

        Request request = new Request.Builder()
                .url(URL+path)
                .post(body)
                .build();

        try {
            resonse = httpClient.newCall(request).execute();
            if(resonse.code() == 404) {
                throw new DockerServerException("Error starting container with path:" + URL+path + "\nMessage from Docker Daemon: " +resonse.body().string());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
