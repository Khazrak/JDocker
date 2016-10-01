package se.codeslasher.docker.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.utils.URLResolver;
import se.codeslasher.docker.exception.DockerServerException;
import se.codeslasher.docker.model.api124.Volume;
import se.codeslasher.docker.model.api124.requests.VolumeCreateRequest;
import se.codeslasher.docker.model.api124.parameters.ListVolumeParams;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by karl on 9/24/16.
 */
public class DockerVolumesHandler {

    private static final Logger logger = LoggerFactory.getLogger(DockerVolumesHandler.class);

    private final ObjectMapper mapper;
    private OkHttpExecuter okHttpExecuter;


    public DockerVolumesHandler(OkHttpClient httpClient, URLResolver urlResolver, ObjectMapper mapper, String url) {
        this.okHttpExecuter = new OkHttpExecuter(httpClient, url, urlResolver);
        this.mapper = mapper;
    }

    public List<Volume> listVolumes() {
        logger.debug("Listing volumes");
        final String path = "v1.24/volumes";

        try {

            Response response = okHttpExecuter.get(path);
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);

            JsonNode volumeJson = mapper.readTree(responseBody);

            volumeJson = volumeJson.get("Volumes");
            Volume[] volumes = mapper.readValue(volumeJson.toString(), Volume[].class);

            return Arrays.asList(volumes);

        } catch (IOException e) {
            logger.error("Exception during listing volumes", e);
        }

        return null;
    }

    public List<Volume> listVolumes(ListVolumeParams params) {
        logger.debug("Listing volumes with params {}", params);
        final String path = "v1.24/volumes";

        try {

            Response response = okHttpExecuter.get(path, params.getQueries());
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);

            JsonNode volumeJson = mapper.readTree(responseBody);

            volumeJson = volumeJson.get("Volumes");
            Volume[] volumes = mapper.readValue(volumeJson.toString(), Volume[].class);

            return Arrays.asList(volumes);

        } catch (IOException e) {
            logger.error("Exception during listing volumes", e);
        }

        return null;
    }

    public Volume createVolume(VolumeCreateRequest volumeCreateRequest) {
        logger.debug("Creating volume");
        final String path = "v1.24/volumes/create";


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
        final String path = "v1.24/volumes/" + id;

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
        final String path = "v1.24/volumes/" + id;
        Response response = okHttpExecuter.delete(path);
    }
}
