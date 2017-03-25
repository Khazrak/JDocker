package com.github.khazrak.jdocker.api126.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.khazrak.jdocker.abstraction.Network;
import com.github.khazrak.jdocker.abstraction.NetworkConnectRequest;
import com.github.khazrak.jdocker.abstraction.NetworkCreateRequest;
import com.github.khazrak.jdocker.abstraction.NetworkListParams;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.api126.requests.NetworkDisconnectRequest126;
import com.github.khazrak.jdocker.utils.OkHttpExecuter;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DockerNetworkHandler {

    private static final Logger logger = LoggerFactory.getLogger(DockerNetworkHandler.class);
    private final ObjectMapper mapper;
    private OkHttpExecuter okHttpExecuter;
    private static final String VERSION = DefaultDockerClient126.API_VERSION;

    public DockerNetworkHandler(OkHttpExecuter okHttpExecuter, ObjectMapper mapper) {
        this.mapper = mapper;
        this.okHttpExecuter = okHttpExecuter;
    }

    public String createNetwork(NetworkCreateRequest networkRequest) {
        logger.debug("Creating network");
        final String path = VERSION + "/networks/create";

        try {
            String json = mapper.writeValueAsString(networkRequest);
            Response response = okHttpExecuter.post(path, json);
            JsonNode jsonNode = mapper.readTree(response.body().string());
            String warn = jsonNode.get("Warning").textValue();

            if(warn.length() > 0) {
                logger.warn(warn);
            }

            logger.debug("Network created with id {}", jsonNode.get("Id").textValue());

            return jsonNode.get("Id").textValue();

        } catch (IOException e) {
            logger.error("Exception during network creation", e);
        }

        return null;
    }

    public List<Network> listNetworks() {
        logger.debug("Listing networks");
        final String path = VERSION + "/networks";

        try {

            Response response = okHttpExecuter.get(path);

            String responseBody = response.body().string();
            logger.debug("Response body: {}",responseBody);

            Network[] networkArray = mapper.readValue(responseBody,Network[].class);
            return Arrays.asList(networkArray);

        } catch (IOException e) {
            logger.error("Exception during listing of networks", e);
        }

        return null;
    }

    public List<Network> listNetworks(NetworkListParams params) {
        logger.debug("Listing networks");
        final String path = VERSION + "/networks";

        try {

            Response response = okHttpExecuter.get(path, params.getQueries());

            String responseBody = response.body().string();
            logger.debug("Response body: {}",responseBody);

            Network[] networkArray = mapper.readValue(responseBody,Network[].class);

            return Arrays.asList(networkArray);

        } catch (IOException e) {
            logger.error("Exception during listing of networks", e);
        }

        return null;
    }

    public void removeNetwork(String id) {
        logger.debug("Removing network {}", id);
        final String path = VERSION + "/networks/"+id;
        Response response = okHttpExecuter.delete(path);
    }

    public Network inspectNetwork(String id) {
        logger.debug("Inspecting network {}", id);
        final String path = VERSION + "/networks/"+id;

        try {
            Response response = okHttpExecuter.get(path);

            String responseBody = response.body().string();
            logger.debug("Response body: {}",responseBody);
            return mapper.readValue(responseBody,Network.class);

        } catch (IOException e) {
            logger.error("Exception during inspecting network "+id, e);
        }

        return null;
    }

    public void connectContainerToNetwork(NetworkConnectRequest networkConnectRequest) {
        logger.debug("Connect container to a network");
        final String path = VERSION + "/networks/"+networkConnectRequest.getNetworkName()+"/connect";

        try {
            String json = mapper.writeValueAsString(networkConnectRequest);
            Response response = okHttpExecuter.post(path, json);

        } catch (JsonProcessingException e) {
            logger.error("Exception during connectContainerToNetwork, problem with mapping NetworkConnectRequest to json", e);
        }

    }

    public void disconnectContainerFromNetwork(String containerName, String networkName, boolean force) {
        logger.debug("Disconnecting container {} from network {}, force: {}", containerName, networkName, force);
        final String path = VERSION + "/networks/"+ networkName +"/disconnect";
        NetworkDisconnectRequest126 networkDisconnectRequest = NetworkDisconnectRequest126
                .builder()
                .container(containerName)
                .force(force)
                .build();

        try {
            String json = mapper.writeValueAsString(networkDisconnectRequest);
            Response response = okHttpExecuter.post(path, json);

        } catch (JsonProcessingException e) {
            logger.error("Exception during disconnectContainerFromNetwork, problem with mapping NetworkDisconnectRequest to json", e);
        }
    }

}
