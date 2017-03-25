package com.github.khazrak.jdocker.api126.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.khazrak.jdocker.abstraction.*;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.utils.OkHttpExecuter;
import com.github.khazrak.jdocker.utils.DockerLogsInputStream;
import com.github.khazrak.jdocker.utils.DockerLogsLineReader;
import com.github.khazrak.jdocker.utils.RequestStreamBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class DockerContainerHandler {

    private static final Logger logger = LoggerFactory.getLogger(DockerContainerHandler.class);
    private final ObjectMapper mapper;
    private OkHttpExecuter okHttpExecuter;
    private static final String VERSION = DefaultDockerClient126.API_VERSION;

    public DockerContainerHandler(OkHttpExecuter okHttpExecuter, ObjectMapper mapper) {
        this.mapper = mapper;
        this.okHttpExecuter = okHttpExecuter;
    }


    public String createContainer(ContainerCreationRequest request) {
        logger.debug("Creating container");
        final String path = VERSION + "/containers/create";

        String id = null;

        Response response = null;
        try {
            String json = mapper.writeValueAsString(request);
            logger.debug("Creating container with json: {}", json);
            Map<String, String> queries = new TreeMap<>();
            queries.put("name", request.getName());

            response = okHttpExecuter.post(path, queries, json);
            id = response.body().string();
            id = mapper.readTree(id).findValue("Id").asText();
            logger.debug("Created container with Id: {}", id);
        } catch (IOException e) {
            logger.error("Exception during container creation", e);
        }
        return id;
    }

    public List<Container> listContainers() {
        logger.debug("Listing containers");
        final String path = VERSION + "/containers/json";
        try {
            Response response = okHttpExecuter.get(path);
            String responseBody = response.body().string();
            logger.debug("Response: {}", responseBody);
            Container[] containers = mapper.readValue(responseBody, Container[].class);
            return Arrays.asList(containers);
        } catch (IOException e) {
            logger.error("Exception during listing of containers", e);
        }
        return null;
    }

    public List<Container> listContainers(ListContainerParams listParams) {
        logger.debug("Listing containers with params: {}", listParams);
        final String path = VERSION + "/containers/json";
        try {
            Response response = okHttpExecuter.get(path, listParams.getQueries());
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);
            Container[] containers = mapper.readValue(responseBody, Container[].class);
            return Arrays.asList(containers);
        } catch (IOException e) {
            logger.error("Exception during listing of containers with params " + listParams, e);
        }
        return null;
    }

    public void kill(String id) {
        logger.debug("Killing container {}", id);
        final String path = VERSION + "/containers/" + id + "/kill";
        Response response = okHttpExecuter.post(path);
    }

    public void kill(String id, String killSignal) {
        logger.debug("Killing container {} with signal: {}", id, killSignal);
        final String path = VERSION + "/containers/" + id + "/kill";
        Map<String, String> queries = new TreeMap<>();
        queries.put("signal", killSignal);

        Response response = okHttpExecuter.post(path, queries);
    }

    public ContainerInspect inspectContainer(String id, boolean size) {
        logger.debug("Inspect container with {}, parameter: size={}", id, size);
        final String path = VERSION + "/containers/" + id + "/json";

        Map<String, String> queries = new TreeMap<>();
        queries.put("size", Boolean.toString(size));

        Response response = null;

        try {
            response = okHttpExecuter.get(path, queries);
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);
            return mapper.readValue(responseBody, ContainerInspect.class);
        } catch (IOException e) {
            logger.error("Exception during inspecting container " + id, e);
        }

        return null;
    }

    public void stop(String id, int secondsUntilKill) {
        logger.debug("Stopping container {}, seconds until issuing kill {}", id, secondsUntilKill);
        final String path = VERSION + "/containers/" + id + "/stop";

        Map<String, String> queries = new TreeMap<>();
        queries.put("t", Integer.toString(secondsUntilKill));

        Response response = okHttpExecuter.post(path, queries);
        if (response.code() == 304) {
            logger.warn("Container already stopped: " + id);
        }
    }

    public void start(String id) {
        logger.debug("Starting container {}", id);
        final String path = VERSION + "/containers/" + id + "/start";

        Response response = okHttpExecuter.post(path);
        if (response.code() == 304) {
            logger.warn("Container already started: " + id);
        }
    }

    public List<String> logs(String id, DockerLogsParameters params) {
        logger.debug("Reading logs as List for container {}, with params {} ", id, params);
        final String path = VERSION + "/containers/" + id + "/logs";
        List<String> logLines = null;

        Response response = okHttpExecuter.get(path, params.getQueryMap());
        if (response == null) {
            logger.error("Failed getting response while reading logs from container {} with params {}", id, params);
            return null; //fail fast
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream()))) {
            logLines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                logLines.add(line);
            }
        } catch (IOException e) {
            logger.error("Exception during reading of logs from container " + id + " with params " + params.toString(), e);
        }

        return logLines;
    }

    public DockerLogsLineReader logsSpecial(String id, DockerLogsParameters params) {
        logger.debug("Reading logs as DockerLogsLineReader for container {}, with params {} ", id, params);
        final String path = "v1.24/containers/" + id + "/logs";
        Response response = okHttpExecuter.get(path, params.getQueryMap());
        return new DockerLogsLineReader(response.body().byteStream());
    }

    public InputStream logsRawStream(String id, DockerLogsParameters params) {
        logger.debug("Reading logs as raw stream for container {}, with params {} ", id, params);
        final String path = "v1.24/containers/" + id + "/logs";
        Response response = okHttpExecuter.get(path, params.getQueryMap());
        return response.body().byteStream();
    }

    public InputStream logsStream(String id, DockerLogsParameters params) {
        logger.debug("Reading logs as DockerLogsInputStream for container {}, with params {} ", id, params);
        final String path = "v1.24/containers/" + id + "/logs";

        Response response = okHttpExecuter.get(path, params.getQueryMap());

        try {
            return new DockerLogsInputStream(response.body().byteStream());
        } catch (IOException e) {
            logger.error("Exception during logs streaming of container " + id, e);
        }

        return null;
    }

    public void pause(String id) {
        logger.debug("Pausing container {}", id);
        final String path = VERSION + "/containers/" + id + "/pause";
        Response response = okHttpExecuter.post(path);
    }

    public void unpause(String id) {
        logger.debug("Unpausing container {}", id);
        final String path = VERSION + "/containers/" + id + "/unpause";
        Response response = okHttpExecuter.post(path);
    }

    public ContainerProcesses top(String id, String arg) {
        logger.debug("Checking processin in container: {}, with ps_args={}", id, arg);
        final String path = "v1.24/containers/" + id + "/top";

        Map<String, String> queries = new TreeMap<>();

        if (arg != null) {
            queries.put("ps_args", arg);
        }

        try {
            Response response = okHttpExecuter.get(path, queries);
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);
            return mapper.readValue(responseBody, ContainerProcesses.class);
        } catch (IOException e) {
            logger.error("Exception during checking containers process for " + id, e);
        }

        return null;
    }

    public void waitForContainerStop(String id) {
        logger.debug("Wait for container {}", id);
        final String path = VERSION + "/containers/" + id + "/wait";
        Response response = okHttpExecuter.post(path);
    }

    public InputStream statsStream(String id) {
        logger.debug("Streaming stats for container {}", id);
        final String path = VERSION + "/containers/" + id + "/stats";

        Map<String, String> queries = new TreeMap<>();
        queries.put("stream", Boolean.toString(true));

        Response response = okHttpExecuter.get(path, queries);
        return response.body().byteStream();
    }

    public ContainerStats stats(String id) {
        logger.debug("Checking stats for container {}", id);
        final String path = VERSION + "/containers/" + id + "/stats";

        Map<String, String> queries = new TreeMap<>();
        queries.put("stream", Boolean.toString(false));

        try {
            Response response = okHttpExecuter.get(path, queries);
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);
            return mapper.readValue(responseBody, ContainerStats.class);

        } catch (IOException e) {
            logger.error("Exception during checking stats for " + id, e);
        }

        return null;
    }

    public Warnings update(String id, ContainerUpdateRequest updateRequest) {
        logger.debug("Updating container config");
        final String path = VERSION + "/containers/" + id + "/update";

        try {
            String json = mapper.writeValueAsString(updateRequest);
            Response response = okHttpExecuter.post(path, json);
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);
            return mapper.readValue(responseBody, Warnings.class);

        } catch (IOException e) {
            logger.error("Exception during updating container config for container " + id, e);
        }

        return null;
    }

    public void restart(String id, int wait) {
        logger.debug("Restarting container {}, wait {}", id, wait);
        final String path = VERSION + "/containers/" + id + "/restart";
        Map<String, String> queries = new TreeMap<>();
        queries.put("t", Integer.toString(wait));

        Response response = okHttpExecuter.post(path, queries);
    }

    public void rename(String originalName, String newName) {
        logger.debug("Renaming container from {} to {}", originalName, newName);
        final String path = VERSION + "/containers/" + originalName + "/rename";

        Map<String, String> queries = new TreeMap<>();
        queries.put("name", newName);
        Response response = okHttpExecuter.post(path, queries);
    }

    public void resizeTty(String id, int width, int height) {
        logger.debug("Resizing TTY with id: {}, with new width: {} and new height: {}", id, width, height);
        final String path = VERSION + "/containers/" + id + "/resize";

        Map<String, String> queries = new TreeMap<>();
        queries.put("h", Integer.toString(height));
        queries.put("w", Integer.toString(width));

        Response response = okHttpExecuter.post(path, queries);
    }

    public void remove(String id) {
        logger.debug("Removing container with id {}", id);
        final String path = VERSION + "/containers/" + id;

        Response response = okHttpExecuter.delete(path);
        if (response.code() == 304) {
            logger.warn("Container already removed: " + id);
        }
    }

    public void remove(String id, boolean forceRemove, boolean removeVolume) {
        logger.debug("Removing container with id {} forceRemove: {} remove volume: {}", id, forceRemove, removeVolume);
        final String path = VERSION + "/containers/" + id;

        Map<String, String> queries = new TreeMap<>();
        queries.put("force", Boolean.toString(forceRemove));
        queries.put("v", Boolean.toString(removeVolume));

        Response response = okHttpExecuter.delete(path, queries);
        if (response.code() == 304) {
            logger.warn("Container already removed: " + id);
        }
    }

    public FileSystemInfo fileSystemInfo(String id, String pathInContainer) {
        logger.debug("FileSystemInfo for container {} with path {}", id, pathInContainer);
        final String path = VERSION + "/containers/" + id + "/archive";
        Map<String, String> queries = new TreeMap<>();
        queries.put("path",pathInContainer);
        Response response = okHttpExecuter.head(path, queries);
        try {

            String responseHead = response.header("X-Docker-Container-Path-Stat");
            logger.debug("Response head base64: {}", responseHead);
            responseHead = new String(Base64.getDecoder().decode(responseHead));
            logger.debug("Response head: {}", responseHead);
            return mapper.readValue(responseHead, FileSystemInfo.class);

        } catch (IOException e) {
            logger.debug("Exception during fileSystemInfo, due to json deserialization", e);
        }

        return null;
    }

    public InputStream fileSystemArchiveDownload(String id, String pathInContainer) {
        logger.debug("FileSystemArchiveDownload for container {} with path {}", id, pathInContainer);
        final String path = VERSION + "/containers/" + id + "/archive";
        Map<String, String> queries = new TreeMap<>();
        queries.put("path",pathInContainer);
        Response response = okHttpExecuter.get(path, queries);
        return response.body().byteStream();
    }

    public void fileSystemArchiveUpload(String id, String pathInContainer, RequestStreamBody body) {
        logger.debug("FileSystemArchiveUpload for container {} with path {}", id, pathInContainer);
        final String path = "v1.24/containers/" + id + "/archive";
        Map<String, String> queries = new TreeMap<>();
        queries.put("path", pathInContainer);
        Response response = okHttpExecuter.put(path, queries, body);
    }

    public String commitContainer(ContainerCommitRequest containerCommitRequest) {
        logger.debug("Commit container {}", containerCommitRequest.getContainerName());
        final String path = VERSION + "/commit";

        try {
            String json = mapper.writeValueAsString(containerCommitRequest.getContainerCommit());
            Response response = okHttpExecuter.post(path, containerCommitRequest.getQueries(), json);
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);
            return mapper.readTree(responseBody).get("Id").textValue();
        } catch (IOException e) {
            logger.error("Exception during commit due to JsonProcessing", e);
        }

        return null;
    }

    public List<ContainerFileSystemChange> containerFileSystemChanges(String id) {
        logger.debug("Checking container filesystem changes for id: {}", id);
        final String path = VERSION + "/containers/" + id + "/changes";

        try {
            Response response = okHttpExecuter.get(path);
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);
            ContainerFileSystemChange[] array = mapper.readValue(responseBody, ContainerFileSystemChange[].class);
            return Arrays.asList(array);

        } catch (IOException e) {
            logger.error("Exception during checking container filesystem changes for " + id, e);
        }

        return null;
    }
}
