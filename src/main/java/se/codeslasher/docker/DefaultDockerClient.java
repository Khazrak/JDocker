package se.codeslasher.docker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by karl on 9/5/16.
 */
public class DefaultDockerClient implements DockerClient {

    private OkHttpClient httpClient;

    public DefaultDockerClient() {
        httpClient = new OkHttpClient();
    }

    public void close()  {
        httpClient = null;
    }

    @Override
    public String createContainer(ContainerCreation spec) {
        String id = null;

        Gson gson = new GsonBuilder().serializeNulls().create();

        RequestBody body = RequestBody.create(ContainerCreation.JSON, gson.toJson(spec));

        Request request = new Request.Builder()
                .url("http://127.0.0.1:9779"+ContainerCreation.PATH)
                .post(body)
                .build();
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            id = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;

    }

}
