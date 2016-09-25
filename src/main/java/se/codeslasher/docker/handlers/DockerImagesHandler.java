package se.codeslasher.docker.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.ContainerCreation;
import se.codeslasher.docker.exception.DockerServerException;
import se.codeslasher.docker.model.api124.*;
import se.codeslasher.docker.utils.Filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created by karl on 9/22/16.
 */
public class DockerImagesHandler {

    private static final Logger logger = LoggerFactory.getLogger(DockerImagesHandler.class);

    private final OkHttpClient httpClient;
    private final ObjectMapper mapper;
    private final String URL;

    public DockerImagesHandler(OkHttpClient httpClient, ObjectMapper mapper, String url) {

        this.httpClient = httpClient;
        this.mapper = mapper;
        this.URL = url;
    }


    public List<ImageInfo> listImages(boolean all) {
        ListImagesParams params = ListImagesParams.builder().all(all).build();
        return listImages(params);
    }

    public List<ImageInfo> listImages(ListImagesParams params) {
        String queryParameters = parseParameters(params);
        String json = null;
        try {
            json = parseFilters(params);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String path = null;
        if (json == null) {
            path = "/v1.24/images/json" + queryParameters;
        } else {
            try {
                path = "/v1.24/images/json" + queryParameters + "&filters=" + URLEncoder.encode(json, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        Response response;
        Request request = new Request.Builder()
                .url(URL + path)
                .get()
                .build();

        try {
            response = httpClient.newCall(request).execute();
            if (response.code() != 200) {
                throw new DockerServerException("Error stopping container with path:" + URL + path + "\nMessage from Docker Daemon: " + response.body().string()
                        + "\nHTTP-Code: " + response.code());
            }


            //System.out.println(response.body().string());
            //return containerListParse(response.body().string());
            ImageInfo[] imageInfos = mapper.readValue(response.body().string(), ImageInfo[].class);
            return Arrays.asList(imageInfos);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String pull(DockerImageName image) {
        final String path = getUrl(image);
        RequestBody body = RequestBody.create(ContainerCreation.JSON, "");
        Response response;


        Map<String, String> headersMap = new TreeMap<>();
        return pull("", image);
    }

    public void pull(DockerImageName image, AuthConfig authConfig) {
        final String path = getUrl(image);
        try {
            String jsonHeader = mapper.writeValueAsString(authConfig);
            jsonHeader = getBase64EncodedJson(jsonHeader);
            pull(jsonHeader, image);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }

    public String pull(DockerImageName image, String token) {
        final String path = getUrl(image);
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

    private String getUrl(DockerImageName image) {
        return "/v1.24/images/create?fromImage=" + image.toStringWithoutTag() + "?tag=" + image.getTag();
    }

    private String pull(String encodedAuthJson, DockerImageName image) {
        final String path = "/v1.24/images/create?fromImage=" + image.toStringWithoutTag() + "&tag=" + image.getTag();
        RequestBody body = RequestBody.create(ContainerCreation.JSON, "");
        String result = "";
        Response response;


        Map<String, String> headersMap = new TreeMap<>();
        headersMap.put("Content-Type", "application/json");
        headersMap.put("X-Registry-Auth", encodedAuthJson);
        Headers headers = Headers.of(headersMap);

        Request request = new Request.Builder()
                .headers(headers)
                .url(URL + path)
                .post(body)
                .build();

        try {
            response = httpClient.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            logger.info(e.getLocalizedMessage(), e);
        }

        return result;
    }

    private String getBase64EncodedJson(String json) {
        return Base64.getEncoder().encodeToString(json.getBytes());
    }

    private String parseParameters(ListImagesParams params) {
        String queryParameters = "?all=" + params.isAll();

        if (params.getFilterByName() != null) {
            queryParameters += "&filter=" + params.getFilterByName();
        }

        return queryParameters;
    }

    private String parseFilters(ListImagesParams params) throws JsonProcessingException {
        Filters filters = new Filters(mapper);

        if (params.getLabels().keySet().size() > 0) {
            filters.add(params.getLabels());
        }
        if (params.getBefore() != null) {
            filters.add("before", params.getBefore());
        }
        if (params.getSince() != null) {
            filters.add("since", params.getSince());
        }
        if (params.isDangling()) {
            filters.add("dangling", "true");
        }

        if (filters.size() == 0) {
            return null;
        }
        return filters.toString();
    }

    public Image inspectImage(DockerImageName imageName) {
        final String path;
        try {
            path = "/v1.24/images/" + URLEncoder.encode(imageName.toString(), StandardCharsets.UTF_8.toString()) + "/json";

            Response response;
            Request request = new Request.Builder()
                    .url(URL + path)
                    .get()
                    .build();
            response = httpClient.newCall(request).execute();
            if (response.code() != 200) {
                throw new DockerServerException("Error inspecting image with path:" + URL + path + "\nMessage from Docker Daemon: " + response.body().string()
                        + "\nHTTP-Code: " + response.code());
            }

            String responseBody = response.body().string();
            logger.debug("Response: {}", responseBody);

            return mapper.readValue(responseBody, Image.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
