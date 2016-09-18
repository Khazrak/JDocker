package se.codeslasher.docker.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.ContainerCreation;
import se.codeslasher.docker.model.api124.AuthConfig;
import se.codeslasher.docker.model.api124.DockerImageName;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by karl on 9/18/16.
 */
public class DockerPullHandler {

    private OkHttpClient httpClient;
    private ObjectMapper mapper;
    private final String URL;
    private static Logger logger = LoggerFactory.getLogger(DockerPullHandler.class);

    public DockerPullHandler(OkHttpClient httpClient, ObjectMapper mapper, String url) {
        this.httpClient = httpClient;
        this.mapper = mapper;
        this.URL = url;
    }


    public String pull(DockerImageName image) {
        final String path = getUrl(image);
        RequestBody body = RequestBody.create(ContainerCreation.JSON,"");
        Response response;


        Map<String, String> headersMap = new TreeMap<>();
        return pull("",image);
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

        map.put("registrytoken",token);

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
        return "/v1.24/images/create?fromImage="+image.toStringWithoutTag() + "?tag=" + image.getTag();
    }

    private String pull(String encodedAuthJson, DockerImageName image) {
        final String path = "/v1.24/images/create?fromImage="+image.toStringWithoutTag() + "&tag=" + image.getTag();
        RequestBody body = RequestBody.create(ContainerCreation.JSON,"");
        String result = "";
        Response response;


        Map<String, String> headersMap = new TreeMap<>();
        headersMap.put("Content-Type","application/json");
        headersMap.put("X-Registry-Auth", encodedAuthJson);
        Headers headers = Headers.of(headersMap);

        Request request = new Request.Builder()
                .headers(headers)
                .url(URL+path)
                .post(body)
                .build();

        try {
            response = httpClient.newCall(request).execute();
            result =  response.body().string();
        }
        catch (IOException e) {
            logger.info(e.getLocalizedMessage(),e);
        }

        return result;
    }

    private String getBase64EncodedJson(String json) {
        return Base64.getEncoder().encodeToString(json.getBytes());
    }

}
