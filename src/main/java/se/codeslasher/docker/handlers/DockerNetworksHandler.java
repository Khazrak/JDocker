/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.codeslasher.docker.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.model.api124.Network;
import se.codeslasher.docker.model.api124.parameters.NetworkListParams;
import se.codeslasher.docker.model.api124.requests.NetworkConnectRequest;
import se.codeslasher.docker.model.api124.requests.NetworkCreateRequest;
import se.codeslasher.docker.model.api124.requests.NetworkDisconnectRequest;
import se.codeslasher.docker.utils.URLResolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DockerNetworksHandler {

    private static Logger logger = LoggerFactory.getLogger(DockerNetworksHandler.class);

    private final ObjectMapper mapper;
    private final OkHttpExecuter okHttpExecuter;

    public DockerNetworksHandler(OkHttpClient httpClient, URLResolver urlResolver, ObjectMapper mapper, String url) {
        this.okHttpExecuter = new OkHttpExecuter(httpClient,url,urlResolver);
        this.mapper = mapper;
    }


    public String createNetwork(NetworkCreateRequest networkRequest) {
        logger.debug("Creating network");
        final String path = "v1.24/networks/create";


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
        final String path = "v1.24/networks";

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
        final String path = "v1.24/networks";

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

    public void connectContainerToNetwork(NetworkConnectRequest networkConnectRequest) {
        logger.debug("Connect container to a network");
        final String path = "v1.24/networks/"+networkConnectRequest.getNetworkName()+"/connect";

        try {
            String json = mapper.writeValueAsString(networkConnectRequest);
            Response response = okHttpExecuter.post(path, json);

        } catch (JsonProcessingException e) {
            logger.error("Exception during connectContainerToNetwork, problem with mapping NetworkConnectRequest to json", e);
        }

    }

    public void disconnectContainerFromNetwork(String containerName, String networkName, boolean force) {
        logger.debug("Disconnecting container {} from network {}, force: {}", containerName, networkName, force);
        final String path = "v1.24/networks/"+ networkName +"/disconnect";
        NetworkDisconnectRequest networkDisconnectRequest = NetworkDisconnectRequest
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

    public Network inspectNetwork(String id) {
        logger.debug("Inspecting network {}", id);
        final String path = "v1.24/networks/"+id;

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

    public void removeNetwork(String id) {
        logger.debug("Removing network {}", id);
        final String path = "v1.24/networks/"+id;
        Response response = okHttpExecuter.delete(path);
    }
}
