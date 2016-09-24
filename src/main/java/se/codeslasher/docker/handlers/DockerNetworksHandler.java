package se.codeslasher.docker.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import se.codeslasher.docker.model.api124.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * Created by karl on 9/23/16.
 */
public class DockerNetworksHandler {

    private static Logger logger = LoggerFactory.getLogger(DockerNetworksHandler.class);

    private final OkHttpClient httpClient;
    private final ObjectMapper mapper;
    private final String URL;

    public DockerNetworksHandler(OkHttpClient httpClient, ObjectMapper mapper, String url) {

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

    public List<Network> listNetworks() {
        final String path = "/v1.24/networks";
        return listNetworksCommand(path);
    }

    public List<Network> listNetworks(NetworkListParams params) {
        String path = null;
        try {
            path = "/v1.24/networks?filters="+ URLEncoder.encode(params.toString(), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return listNetworksCommand(path);
    }

    private List<Network> listNetworksCommand(final String path) {
        Response response;
        Request request = new Request.Builder()
                .url(URL+path)
                .get()
                .build();

        try {

            response = httpClient.newCall(request).execute();
            if(response.code() != 200 ) {
                throw new DockerServerException("Error listing network with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }

            String responseBody = response.body().string();
            logger.debug("Response: {}",responseBody);

            Network[] networkArray = mapper.readValue(responseBody,Network[].class);

            return Arrays.asList(networkArray);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void connectContainerToNetwork(NetworkConnectRequest networkConnectRequest) {
        final String path = "/v1.24/networks/"+networkConnectRequest.getNetworkName()+"/connect";

        try {
            String json = mapper.writeValueAsString(networkConnectRequest);
            Response response;
            RequestBody body = RequestBody.create(ContainerCreation.JSON, json);
            Request request = new Request.Builder()
                    .url(URL+path)
                    .post(body)
                    .build();

            response = httpClient.newCall(request).execute();
            if (response.code() != 200) {
                throw new DockerServerException("Error connecting container to network with path:" + URL + path + "\nMessage from Docker Daemon: " + response.body().string()
                        + "\nHTTP-Code: " + response.code());
            }

            System.out.println("Response: "+response.body().string());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void disconnectContainerFromNetwork(String containerName, String networkName, boolean force) {
        final String path = "/v1.24/networks/"+ networkName +"/disconnect";
        NetworkDisconnectRequest networkDisconnectRequest = NetworkDisconnectRequest.builder().container("mongo").build();
        try {
            String json = mapper.writeValueAsString(networkDisconnectRequest);
            Response response;
            RequestBody body = RequestBody.create(ContainerCreation.JSON, json);
            Request request = new Request.Builder()
                    .url(URL+path)
                    .post(body)
                    .build();

            response = httpClient.newCall(request).execute();
            if (response.code() != 200) {
                throw new DockerServerException("Error disconnecting container to network with path:" + URL + path + "\nMessage from Docker Daemon: " + response.body().string()
                        + "\nHTTP-Code: " + response.code());
            }

            System.out.println("Response: "+response.body().string());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Network inspectNetwork(String id) {
        final String path = "/v1.24/networks/"+id;

        Response response;
        Request request = new Request.Builder()
                .url(URL+path)
                .get()
                .build();

        try {

            response = httpClient.newCall(request).execute();
            if(response.code() != 200 ) {
                throw new DockerServerException("Error inspecting network with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }

            String responseBody = response.body().string();
            logger.debug("Response: {}",responseBody);

            return mapper.readValue(responseBody,Network.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void removeNetwork(String id) {
        final String path = "/v1.24/networks/"+id;

        Response response;
        Request request = new Request.Builder()
                .url(URL+path)
                .delete()
                .build();

        try {

            response = httpClient.newCall(request).execute();
            if(response.code() != 204 ) {
                throw new DockerServerException("Error removing network with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
