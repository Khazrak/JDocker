package se.codeslasher.docker.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;

/**
 * Created by karl on 9/24/16.
 */
public class DockerVolumesHandler {
    private final OkHttpClient httpClient;
    private final ObjectMapper mapper;
    private final String URL;

    public DockerVolumesHandler(OkHttpClient httpClient, ObjectMapper mapper, String url) {
        this.httpClient = httpClient;
        this.mapper = mapper;
        this.URL = url;
    }
}
