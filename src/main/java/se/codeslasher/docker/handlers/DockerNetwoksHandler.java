package se.codeslasher.docker.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.ContainerCreation;
import se.codeslasher.docker.DockerNetworkCreateRequest;
import se.codeslasher.docker.exception.DockerServerException;

import java.io.IOException;

/**
 * Created by karl on 9/23/16.
 */
public class DockerNetwoksHandler {

    private static Logger logger = LoggerFactory.getLogger(DockerNetwoksHandler.class);

    private final OkHttpClient httpClient;
    private final ObjectMapper mapper;
    private final String URL;

    public DockerNetwoksHandler(OkHttpClient httpClient, ObjectMapper mapper, String url) {

        this.httpClient = httpClient;
        this.mapper = mapper;
        this.URL = url;
    }


    public String createNetwork(DockerNetworkCreateRequest networkRequest) {
        final String path = "/v1.24/networks/create";

        Response response;
        try {
            String json = mapper.writeValueAsString(networkRequest);

            RequestBody body = RequestBody.create(ContainerCreation.JSON,json);
            Request request = new Request.Builder()
                    .url(URL+path)
                    .post(body)
                    .build();


            response = httpClient.newCall(request).execute();
            if(response.code() != 201 ) {
                throw new DockerServerException("Error creating network with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }

            JsonNode jsonNode = mapper.readTree(response.body().string());
            String warn = jsonNode.get("Warning").textValue();

            if(warn.length() > 0) {
                logger.warn(warn);
            }

            return jsonNode.get("Id").textValue();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
