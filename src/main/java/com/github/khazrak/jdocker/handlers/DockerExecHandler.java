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
package com.github.khazrak.jdocker.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.khazrak.jdocker.model.api124.ExecInfo;
import com.github.khazrak.jdocker.model.api124.requests.ExecCreateRequest;
import com.github.khazrak.jdocker.utils.URLResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

public class DockerExecHandler {

    private static final Logger logger = LoggerFactory.getLogger(DockerExecHandler.class);

    private final ObjectMapper mapper;
    private final OkHttpExecuter okHttpExecuter;

    public DockerExecHandler(OkHttpClient httpClient, URLResolver urlResolver, ObjectMapper mapper, String url) {
        this.okHttpExecuter = new OkHttpExecuter(httpClient, url, urlResolver);
        this.mapper = mapper;
    }

    public String createExec(String containerId, ExecCreateRequest execCreateRequest) {
        logger.debug("Creating exec for container {}", containerId);
        final String path = "v1.24/containers/" + containerId + "/exec";

        try {
            String json = mapper.writeValueAsString(execCreateRequest);
            Response response = okHttpExecuter.post(path, json);
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);

            return mapper.readTree(responseBody).findValue("Id").asText();

        } catch (IOException e) {
            logger.error("Exception during exec creation for container "+containerId, e);
        }

        return null;
    }

    public ExecInfo inspectExec(String id) {
        logger.debug("Inspecting exec {}", id);
        final String path = "v1.24/exec/" + id + "/json";

        try {
            Response response = okHttpExecuter.get(path);

            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);

            return mapper.readValue(responseBody, ExecInfo.class);

        } catch (IOException e) {
            logger.error("Exception during inspecting exec "+id, e);
        }

        return null;
    }

    public void resizeExec(String id, int width, int height) {
        logger.debug("Resizing exec tty {}, new width {}, new height {}", id, width, height);
        final String path = "v1.24/exec/" + id + "/resize";

        Map<String, String> queries = new TreeMap<>();
        queries.put("w", Integer.toString(width));
        queries.put("h", Integer.toString(height));

        Response response = okHttpExecuter.post(path, queries);
    }

    public void startExec(String id, boolean tty) {
        logger.debug("Starting exec {} with a tty: {}", id, tty);
        final String path = "v1.24/exec/" + id + "/start";

        Map<String, Boolean> startRequest = new TreeMap<>();
        startRequest.put("Detach",true);
        startRequest.put("Tty",tty);

        try {
            String json = mapper.writeValueAsString(startRequest);
            Response response = okHttpExecuter.post(path, json);
        } catch (JsonProcessingException e) {
            logger.error("Exception during start of exec "+id+" due to json serialization problem", e);
        }
    }

    public InputStream startExec(String id) {
        logger.debug("Starting exec {}, streaming");
        final String path = "v1.24/exec/" + id + "/start";

        Map<String, Boolean> startRequest = new TreeMap<>();
        startRequest.put("Detach",false);
        startRequest.put("Tty",true);

        try {
            String json = mapper.writeValueAsString(startRequest);
            Response response = okHttpExecuter.post(path, json);
            return response.body().byteStream();
        } catch (IOException e) {
            logger.error("Exception during exec start of "+id, e);
        }

        return null;
    }
}
