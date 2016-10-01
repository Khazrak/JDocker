package se.codeslasher.docker.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.model.api124.ExecInfo;
import se.codeslasher.docker.model.api124.requests.ExecCreateRequest;
import se.codeslasher.docker.utils.URLResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by karl on 9/29/16.
 */
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
