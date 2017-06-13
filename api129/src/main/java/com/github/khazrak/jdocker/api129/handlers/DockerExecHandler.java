package com.github.khazrak.jdocker.api129.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.khazrak.jdocker.abstraction.ExecCreateRequest;
import com.github.khazrak.jdocker.abstraction.ExecInfo;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.utils.OkHttpExecuter;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

public class DockerExecHandler {

    private static final Logger logger = LoggerFactory.getLogger(DockerExecHandler.class);
    private final ObjectMapper mapper;
    private OkHttpExecuter okHttpExecuter;
    private static final String VERSION = DefaultDockerClient129.API_VERSION;
    private static final String EXEC_URL = VERSION + "/exec/";

    public DockerExecHandler(OkHttpExecuter okHttpExecuter, ObjectMapper mapper) {
        this.mapper = mapper;
        this.okHttpExecuter = okHttpExecuter;
    }


    public String createExec(String containerId, ExecCreateRequest request) {
        logger.debug("Creating exec for container {}", containerId);
        final String path = VERSION + "/containers/" + containerId + "/exec";

        try {
            String json = mapper.writeValueAsString(request);
            Response response = okHttpExecuter.post(path, json);
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);

            return mapper.readTree(responseBody).findValue("Id").asText();

        } catch (IOException e) {
            logger.error("Exception during exec creation for container " + containerId, e);
        }

        return null;
    }

    public void startExec(String id, boolean tty) {
        logger.debug("Starting exec {} with a tty: {}", id, tty);
        final String path = EXEC_URL + id + "/start";

        Map<String, Boolean> startRequest = new TreeMap<>();
        startRequest.put("Detach", true);
        startRequest.put("Tty", tty);

        try {
            String json = mapper.writeValueAsString(startRequest);
            okHttpExecuter.post(path, json);
        } catch (JsonProcessingException e) {
            logger.error("Exception during start of exec " + id + " due to json serialization problem", e);
        }
    }

    public InputStream startExec(String id) {
        logger.debug("Starting exec {}, streaming");
        final String path = EXEC_URL + id + "/start";

        Map<String, Boolean> startRequest = new TreeMap<>();
        startRequest.put("Detach", false);
        startRequest.put("Tty", true);

        try {
            String json = mapper.writeValueAsString(startRequest);
            Response response = okHttpExecuter.post(path, json);
            return response.body().byteStream();
        } catch (IOException e) {
            logger.error("Exception during exec start of " + id, e);
        }

        return null;
    }

    public ExecInfo inspectExec(String id) {
        logger.debug("Inspecting exec {}", id);
        final String path = EXEC_URL + id + "/json";

        try {
            Response response = okHttpExecuter.get(path);

            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);

            return mapper.readValue(responseBody, ExecInfo.class);

        } catch (IOException e) {
            logger.error("Exception during inspecting exec " + id, e);
        }

        return null;
    }

    public void resizeExec(String id, int width, int height) {
        logger.debug("Resizing exec tty {}, new width {}, new height {}", id, width, height);
        final String path = EXEC_URL + id + "/resize";

        Map<String, String> queries = new TreeMap<>();
        queries.put("w", Integer.toString(width));
        queries.put("h", Integer.toString(height));

        okHttpExecuter.post(path, queries);
    }

}
