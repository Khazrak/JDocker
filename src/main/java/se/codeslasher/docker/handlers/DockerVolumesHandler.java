package se.codeslasher.docker.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.exception.DockerServerException;
import se.codeslasher.docker.model.api124.Network;
import se.codeslasher.docker.model.api124.Volume;
import se.codeslasher.docker.model.api124.VolumeListParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * Created by karl on 9/24/16.
 */
public class DockerVolumesHandler {

    private static final Logger logger = LoggerFactory.getLogger(DockerVolumesHandler.class);

    private final OkHttpClient httpClient;
    private final ObjectMapper mapper;
    private final String URL;

    public DockerVolumesHandler(OkHttpClient httpClient, ObjectMapper mapper, String url) {
        this.httpClient = httpClient;
        this.mapper = mapper;
        this.URL = url;
    }

    public List<Volume> listVolumes() {
        String path = "/v1.24/volumes";
        return listVolumesCommand(path);
    }

    public List<Volume> listVolumes(VolumeListParams params) {
        String path = null;
        try {
            path = "/v1.24/volumes?filter="+ URLEncoder.encode(params.toString(), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return listVolumesCommand(path);
    }




    private List<Volume> listVolumesCommand(final String path) {
        Response response;
        Request request = new Request.Builder()
                .url(URL+path)
                .get()
                .build();

        try {

            response = httpClient.newCall(request).execute();
            if(response.code() != 200 ) {
                throw new DockerServerException("Error listing network with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }

            String responseBody = response.body().string();
            logger.debug("Response: {}",responseBody);

            JsonNode volumeJson = mapper.readTree(responseBody);

            volumeJson = volumeJson.get("Volumes");
            Volume[] volumes = mapper.readValue(volumeJson.toString(),Volume[].class);

            return Arrays.asList(volumes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
