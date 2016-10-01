package se.codeslasher.docker.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.exception.DockerServerException;
import se.codeslasher.docker.model.api124.AuthConfig;
import se.codeslasher.docker.model.api124.Image;
import se.codeslasher.docker.model.api124.ImageInfo;
import se.codeslasher.docker.model.api124.parameters.ListImagesParams;
import se.codeslasher.docker.utils.DockerImageName;
import se.codeslasher.docker.utils.URLResolver;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created by karl on 9/22/16.
 */
public class DockerImagesHandler {

    private static final Logger logger = LoggerFactory.getLogger(DockerImagesHandler.class);

    private final ObjectMapper mapper;
    private final OkHttpExecuter okHttpExecuter;

    public DockerImagesHandler(OkHttpClient httpClient, URLResolver urlResolver, ObjectMapper mapper, String url) {
        this.okHttpExecuter = new OkHttpExecuter(httpClient, url, urlResolver);
        this.mapper = mapper;
    }

    public List<ImageInfo> listImages(boolean all) {
        ListImagesParams params = ListImagesParams.builder().all(all).build();
        return listImages(params);
    }

    public List<ImageInfo> listImages(ListImagesParams params) {
        logger.debug("Listing images");
        final String path = "/v1.24/images/json";

        try {
            Response response = okHttpExecuter.get(path, params.getQueries());
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);

            ImageInfo[] imageInfos = mapper.readValue(responseBody, ImageInfo[].class);
            return Arrays.asList(imageInfos);

        } catch (IOException e) {
            logger.error("Exception during listing of images", e);
        }

        return null;
    }


    public String pull(DockerImageName image) {
        logger.debug("Pulling image with name {}", image.toString());
        final String path = "/v1.24/images/create";
        Map<String, String> queries = new TreeMap<>();
        queries.put("fromImage", image.toStringWithoutTag());
        queries.put("tag", image.getTag());

        Map<String, String> headersMap = new TreeMap<>();
        return pull("", image);
    }

    public void pull(DockerImageName image, AuthConfig authConfig) {
        logger.debug("Pulling image with name {}", image.toString());
        final String path = "/v1.24/images/create";
        Map<String, String> queries = new TreeMap<>();
        queries.put("fromImage", image.toStringWithoutTag());
        queries.put("tag", image.getTag());
        try {
            String jsonHeader = mapper.writeValueAsString(authConfig);
            jsonHeader = getBase64EncodedJson(jsonHeader);
            pull(jsonHeader, image);
        } catch (JsonProcessingException e) {
            logger.debug("Exception during oulling if image: "+image.toString()+" due to json serialization of authconfig", e);
        }
    }

    public String pull(DockerImageName image, String token) {
        final String path = "/v1.24/images/create";
        Map<String, String> queries = new TreeMap<>();
        queries.put("fromImage", image.toStringWithoutTag());
        queries.put("tag", image.getTag());

        TreeMap<String, String> map = new TreeMap<>();

        map.put("registrytoken", token);

        String result = null;

        String jsonHeader = null;
        try {
            jsonHeader = mapper.writeValueAsString(map);
            jsonHeader = getBase64EncodedJson(jsonHeader);
            result = pull(jsonHeader, image);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String pull(String encodedAuthJson, DockerImageName image) {
        final String path = "/v1.24/images/create?fromImage=" + image.toStringWithoutTag() + "&tag=" + image.getTag();
        String result = "";

        Map<String, String> headersMap = new TreeMap<>();
        headersMap.put("Content-Type", "application/json");
        headersMap.put("X-Registry-Auth", encodedAuthJson);
        Headers headers = Headers.of(headersMap);


        try {
            Response response = okHttpExecuter.post(headers, path);
            result = response.body().string();
        } catch (IOException e) {
            logger.info("Exception during pulling of image: "+image.toString(), e);
        }

        return result;
    }

    private String getBase64EncodedJson(String json) {
        return Base64.getEncoder().encodeToString(json.getBytes());
    }

    public Image inspectImage(DockerImageName imageName) {
        logger.debug("Inspecting image {}", imageName.toString());
        final String path;
        try {
            path = "/v1.24/images/" + URLEncoder.encode(imageName.toString(), StandardCharsets.UTF_8.toString()) + "/json";
            Response response = okHttpExecuter.get(path);
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);

            return mapper.readValue(responseBody, Image.class);

        } catch (IOException e) {
            logger.error("Exception during inspecting image: "+imageName.toString(), e);
        }

        return null;
    }
}
