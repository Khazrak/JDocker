package se.codeslasher.docker.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.codeslasher.docker.exception.DockerServerException;
import se.codeslasher.docker.model.api124.Image;
import se.codeslasher.docker.model.api124.ListImagesParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * Created by karl on 9/22/16.
 */
public class DockerImagesHandler {


    private final OkHttpClient httpClient;
    private final ObjectMapper mapper;
    private final String URL;

    public DockerImagesHandler(OkHttpClient httpClient, ObjectMapper mapper, String url) {

        this.httpClient = httpClient;
        this.mapper = mapper;
        this.URL = url;
    }


    public List<Image> listImages(boolean all) {
        ListImagesParams params = ListImagesParams.builder().all(all).build();
        return listImages(params);
    }

    public List<Image> listImages(ListImagesParams params) {
        String queryParameters = parseParameters(params);
        String json = null;
        try {
            json = parseFilters(params);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String path = null;
        if(json == null) {
            path = "/v1.24/images/json"+queryParameters;
        }
        else {
            try {
                path = "/v1.24/images/json"+queryParameters + "&filters="+ URLEncoder.encode(json,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        Response response;
        Request request = new Request.Builder()
                .url(URL+path)
                .get()
                .build();

        try {
            response = httpClient.newCall(request).execute();
            if(response.code() != 200 ) {
                throw new DockerServerException("Error stopping container with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }


            //System.out.println(response.body().string());
            //return containerListParse(response.body().string());
            Image[] images = mapper.readValue(response.body().string(), Image[].class);
            return Arrays.asList(images);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseParameters(ListImagesParams params) {
        String queryParameters = "?all="+params.isAll();

        if(params.getFilterByName() != null) {
            queryParameters += "&filter="+params.getFilterByName();
        }

        return queryParameters;
    }

    private String parseFilters(ListImagesParams params) throws JsonProcessingException {
        ObjectNode objectNode = mapper.createObjectNode();
        if(params.getLabels().keySet().size() > 0) {
            for(String s : params.getLabels().keySet()) {
                objectNode.putObject(s).put(params.getLabels().get(s),true);
            }
        }
        if(params.getBefore() != null) {
            objectNode.putObject("before").put(params.getBefore(),true);
        }
        if(params.getSince() != null) {
            objectNode.putObject("since").put(params.getSince(),true);
        }
        if(params.isDangling()) {
            objectNode.putObject("dangling").put("true",true);
        }

        if(objectNode.size() == 0) {
            return null;
        }
        return objectNode.toString();
    }
}
