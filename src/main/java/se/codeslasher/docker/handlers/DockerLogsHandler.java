package se.codeslasher.docker.handlers;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.exception.DockerServerException;
import se.codeslasher.docker.model.api124.DockerLogsParameters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karl on 9/18/16.
 */
public class DockerLogsHandler {

    private OkHttpClient httpClient;
    private Logger logger = LoggerFactory.getLogger(DockerLogsHandler.class);
    private String URL;

    public DockerLogsHandler(OkHttpClient client, String url) {
        this.httpClient = client;
        this.URL = url;
    }

    public List<String> logs(String id, DockerLogsParameters params) {
        List<String> logLines = null;
        final String path = getUrl(id, params);
        logger.info(path);
        Request request = getRequest(path);
        Response response = getResponse(path, request);
        if(response == null) {
            return null;
        }

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream()))) {
            logLines = new ArrayList<>();
            String line;
            while((line = reader.readLine()) != null) {
                logLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logLines;
    }

    public DockerLogsLineReader logsSpecial(String id, DockerLogsParameters params) {
        final String path = getUrl(id, params);
        Request request = getRequest(path);
        Response response = getResponse(path, request);
        return new DockerLogsLineReader(response.body().byteStream());
    }

    public InputStream logsRawStream(String id, DockerLogsParameters params) {
        final String path = getUrl(id, params);
        Request request = getRequest(path);
        Response response = getResponse(path, request);
        return response.body().byteStream();
    }

    private String getUrl(String id, DockerLogsParameters params) {
        return "/v1.24/containers/"+id+"/logs"+params.toString();
    }

    private Request getRequest(String path) {
        return new Request.Builder()
                .url(URL+path)
                .get()
                .build();
    }

    private Response getResponse(String path, Request request) {
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            logger.info("HTTP-Response Code: "+response.code());
            if(response.code() != 200 ) {
                throw new DockerServerException("Error stopping container with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


}
