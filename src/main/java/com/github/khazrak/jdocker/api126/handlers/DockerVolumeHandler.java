package com.github.khazrak.jdocker.api126.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.khazrak.jdocker.abstraction.Volume;
import com.github.khazrak.jdocker.abstraction.VolumeCreateRequest;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.api126.requests.ListVolumeParams;
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
    private static final String VERSION = DefaultDockerClient126.API_VERSION;

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
            logger.debug("Response body: {}", responseBody);

            JsonNode volumeJson = mapper.readTree(responseBody);

            volumeJson = volumeJson.get("Volumes");
            Volume[] volumes = mapper.readValue(volumeJson.toString(), Volume[].class);

            if (volumes == null) return new ArrayList<Volume>();

            return Arrays.asList(volumes);

        } catch (IOException e) {
            logger.error("Exception during listing volumes", e);
        }

        return null;
    }

    public List<Volume> listVolumes(ListVolumeParams params) {
        logger.debug("Listing volumes with params {}", params);
        final String path = VERSION + "/volumes";

        try {

            Response response = okHttpExecuter.get(path, params.getQueries());
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);

            JsonNode volumeJson = mapper.readTree(responseBody);

            volumeJson = volumeJson.get("Volumes");
            Volume[] volumes = mapper.readValue(volumeJson.toString(), Volume[].class);

            if (volumes == null) return new ArrayList<Volume>();

            return Arrays.asList(volumes);

        } catch (IOException e) {
            logger.error("Exception during listing volumes", e);
        }

        return null;
    }

    public Volume createVolume(VolumeCreateRequest volumeCreateRequest) {
        logger.debug("Creating volume");
        final String path = VERSION + "/volumes/create";


        try {
            String json = mapper.writeValueAsString(volumeCreateRequest);

            Response response = okHttpExecuter.post(path, json);

            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);

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
            logger.debug("Response body: {}", responseBody);
            return mapper.readValue(responseBody, Volume.class);

        } catch (IOException e) {
            logger.error("Exception during volume inspection of " + id, e);
        }

        return null;
    }

    public void removeVolume(String id) {
        logger.debug("Removing volume {}", id);
        final String path = VERSION + "/volumes/" + id;
        Response response = okHttpExecuter.delete(path);
    }
}
