package com.github.khazrak.jdocker.api129.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.khazrak.jdocker.abstraction.ListVolumeParams;
import com.github.khazrak.jdocker.abstraction.Volume;
import com.github.khazrak.jdocker.abstraction.VolumeCreateRequest;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.utils.OkHttpExecuter;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DockerVolumeHandler {

    private static final Logger logger = LoggerFactory.getLogger(DockerVolumeHandler.class);
    private final ObjectMapper mapper;
    private OkHttpExecuter okHttpExecuter;
    private static final String VERSION = DefaultDockerClient129.API_VERSION;

    public static final String RESPONSE_BODY_LOG = "Response body: {}";

    public DockerVolumeHandler(OkHttpExecuter okHttpExecuter, ObjectMapper mapper) {
        this.mapper = mapper;
        this.okHttpExecuter = okHttpExecuter;
    }

    public List<Volume> listVolumes() {
        logger.debug("Listing volumes");
        final String path = VERSION + "/volumes";

        try {

            Response response = okHttpExecuter.get(path);
            String responseBody = response.body().string();
            logger.debug(RESPONSE_BODY_LOG, responseBody);

            JsonNode volumeJson = mapper.readTree(responseBody);

            volumeJson = volumeJson.get("Volumes");
            Volume[] volumes = mapper.readValue(volumeJson.toString(), Volume[].class);

            if (volumes == null) return new ArrayList<>();

            return Arrays.asList(volumes);

        } catch (IOException e) {
            logger.error("Exception during listing volumes", e);
        }

        return new ArrayList<>();
    }

    public List<Volume> listVolumes(ListVolumeParams params) {
        logger.debug("Listing volumes with params {}", params);
        final String path = VERSION + "/volumes";

        try {

            Response response = okHttpExecuter.get(path, params.getQueries());
            String responseBody = response.body().string();
            logger.debug(RESPONSE_BODY_LOG, responseBody);

            JsonNode volumeJson = mapper.readTree(responseBody);

            volumeJson = volumeJson.get("Volumes");
            Volume[] volumes = mapper.readValue(volumeJson.toString(), Volume[].class);

            if (volumes == null) return new ArrayList<>();

            return Arrays.asList(volumes);

        } catch (IOException e) {
            logger.error("Exception during listing volumes", e);
        }

        return new ArrayList<>();
    }

    public Volume createVolume(VolumeCreateRequest volumeCreateRequest) {
        logger.debug("Creating volume");
        final String path = VERSION + "/volumes/create";


        try {
            String json = mapper.writeValueAsString(volumeCreateRequest);

            Response response = okHttpExecuter.post(path, json);

            String responseBody = response.body().string();
            logger.debug(RESPONSE_BODY_LOG, responseBody);

            return mapper.readValue(responseBody, Volume.class);

        } catch (IOException e) {
            logger.error("Exception during volume creation", e);
        }

        return null;
    }

    public Volume inspectVolume(String id) {
        logger.debug("Inspecting volume {}", id);
        final String path = VERSION + "/volumes/" + id;

        try {

            Response response = okHttpExecuter.get(path);
            String responseBody = response.body().string();
            logger.debug(RESPONSE_BODY_LOG, responseBody);
            return mapper.readValue(responseBody, Volume.class);

        } catch (IOException e) {
            logger.error("Exception during volume inspection of " + id, e);
        }

        return null;
    }

    public void removeVolume(String id) {
        logger.debug("Removing volume {}", id);
        final String path = VERSION + "/volumes/" + id;
        okHttpExecuter.delete(path);
    }
}
