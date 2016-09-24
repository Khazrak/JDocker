package se.codeslasher.docker.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.ContainerCreation;
import se.codeslasher.docker.ContainerListRequest;
import se.codeslasher.docker.model.api124.ContainerUpdateRequest;
import se.codeslasher.docker.exception.DockerServerException;
import se.codeslasher.docker.model.api124.*;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by karl on 9/23/16.
 */
public class DockerContainerHandler {

    private static final Logger logger = LoggerFactory.getLogger(DockerContainerHandler.class);

    private final OkHttpClient httpClient;
    private final ObjectMapper mapper;
    private final String URL;

    public DockerContainerHandler(OkHttpClient httpClient, ObjectMapper mapper, String url) {

        this.httpClient = httpClient;
        this.mapper = mapper;
        this.URL = url;
    }


    public String createContainer(ContainerCreation spec) {
        String id = null;

        Response response = null;
        try {
            String json = mapper.writeValueAsString(spec);

            RequestBody body = RequestBody.create(ContainerCreation.JSON,json);

            Request request = new Request.Builder()
                    .url(URL+ContainerCreation.PATH+"?name="+spec.getName())
                    .post(body)
                    .build();
            response = httpClient.newCall(request).execute();
            id = response.body().string();

            id = mapper.readTree(id).findValue("Id").asText();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }


    public DockerContainerInspect inspectContainer(String id, boolean size) {
        final String path = "/v1.24/containers/"+id+"/json?size="+size;

        Response response = null;
        Request request = new Request.Builder()
                .url(URL+path)
                .get()
                .build();

        try {
            response = httpClient.newCall(request).execute();
            if(response.code() == 304) {
                logger.warn("Container already stopped: "+id);
            }
            else if(response.code() != 200 ) {
                throw new DockerServerException("Error stopping container with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }
            return mapper.readValue(response.body().string(), DockerContainerInspect.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ContainerProcesses top(String id, String arg) {
        String param = "";
        if(arg != null) {
            try {
                param += "?ps_args="+ URLEncoder.encode(arg, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getLocalizedMessage(),e);
            }
        }
        final String path = "/v1.24/containers/"+id+"/top"+param;

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
            return mapper.readValue(response.body().string(),ContainerProcesses.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<ContainerFileSystemChange> containerFileSystemChanges(String id) {
        final String path = "/v1.24/containers/"+id+"/changes";

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
            ContainerFileSystemChange[] array = mapper.readValue(response.body().string(),ContainerFileSystemChange[].class);
            return Arrays.asList(array);



        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public ContainerStats stats(String id) {
        final String path = "/v1.24/containers/"+id+"/stats?stream=false";

        Response response;
        Request request = new Request.Builder()
                .url(URL+path)
                .get()
                .build();

        try {
            response = httpClient.newCall(request).execute();
            if(response.code() != 200 ) {
                throw new DockerServerException("Error reading stats from container with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }
            return mapper.readValue(response.body().string(), ContainerStats.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public InputStream statsStream(String id) {
        final String path = "/v1.24/containers/"+id+"/stats?stream=true";

        Response response;
        Request request = new Request.Builder()
                .url(URL+path)
                .get()
                .build();

        try {
            response = httpClient.newCall(request).execute();
            if(response.code() != 200 ) {
                throw new DockerServerException("Error reading stats from container with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }
            return response.body().byteStream();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void resizeTty(String id, int width, int height) {
        final String path ="/v1.24/containers/"+id+"/resize?h="+height+"&w="+width;

        Response response;
        RequestBody body = RequestBody.create(ContainerCreation.JSON,"");
        Request request = new Request.Builder()
                .url(URL+path)
                .post(body)
                .build();
        try {
            response = httpClient.newCall(request).execute();
            if(response.code() != 200 ) {
                throw new DockerServerException("Error resizing container tty with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(String id) {
        final String path = "/v1.24/containers/"+id+"/start";

        Response response;
        RequestBody body = RequestBody.create(ContainerCreation.JSON,"");
        Request request = new Request.Builder()
                .url(URL+path)
                .post(body)
                .build();

        try {
            response = httpClient.newCall(request).execute();
            if(response.code() == 304) {
                logger.warn("Container already started: "+id);
            }
            else if(response.code() != 204 ) {
                throw new DockerServerException("Error starting container with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(String id, int secondsUntilKill) {
        final String path = "/v1.24/containers/"+id+"/stop?t="+secondsUntilKill;

        Response response;
        RequestBody body = RequestBody.create(ContainerCreation.JSON,"");
        Request request = new Request.Builder()
                .url(URL+path)
                .post(body)
                .build();

        try {
            response = httpClient.newCall(request).execute();
            if(response.code() == 304) {
                logger.warn("Container already stopped: "+id);
            }
            else if(response.code() != 204 ) {
                throw new DockerServerException("Error stopping container with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public InputStream logsStream(String id, DockerLogsParameters params) {
        final String path = "/v1.24/containers/"+id+"/logs"+params.toString();
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
            return new DockerLogsInputStream(response.body().byteStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Container> listContainers() {
        final String path = "/v1.24/containers/json";
        return runListCommand(path);
    }

    public List<Container> listContainers(ContainerListRequest listRequest) {
        String params = listRequest.toString();
        if(params.length() > 0) {
            return runListCommand("/v1.24/containers/json?"+params);
        }
        else {
            return listContainers();
        }
    }

    public void remove(String id) {
        final String path = "/v1.24/containers/"+id;
        removeContainer(path, id);
    }

    public void remove(String id, boolean forceRemove, boolean removeVolume) {
        final String path = "/v1.24/containers/"+id+"?force="+forceRemove+"&v="+removeVolume;
        removeContainer(path, id);
    }

    public void kill(String id) {
        final String path = "/v1.24/containers/"+id+"/kill";
        killContainer(path,id);
    }

    public void kill(String id, String signal) {
        final String path = "/v1.24/containers/"+id+"/kill?signal="+signal;
        killContainer(path,id);
    }

    public void restart(String id, int wait) {
        final String path = "/v1.24/containers/"+id+"/restart?t="+wait;

        Response response;
        RequestBody body = RequestBody.create(ContainerCreation.JSON,"");
        Request request = new Request.Builder()
                .url(URL+path)
                .post(body)
                .build();

        try {
            response = httpClient.newCall(request).execute();
            if(response.code() != 204 ) {
                throw new DockerServerException("Error restarting container with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Warnings update(String id, ContainerUpdateRequest updateConfig) {
        final String path = "/v1.24/containers/"+id+"/update";

        Response response;

        try {
            String json = mapper.writeValueAsString(updateConfig);
            RequestBody body = RequestBody.create(ContainerCreation.JSON,json);
            Request request = new Request.Builder()
                    .url(URL+path)
                    .post(body)
                    .build();
            response = httpClient.newCall(request).execute();

            if(response.code() != 200 ) {
                throw new DockerServerException("Error updating container config with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }

            return mapper.readValue(response.body().string(), Warnings.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void rename(String originalName, String newName) {
        final String path = "/v1.24/containers/"+originalName+"/rename?name="+newName;

        Response response;
        RequestBody body = RequestBody.create(ContainerCreation.JSON,"");
        Request request = new Request.Builder()
                .url(URL+path)
                .post(body)
                .build();

        try {
            response = httpClient.newCall(request).execute();
            if(response.code() != 204 ) {
                throw new DockerServerException("Error renaming container with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause(String id) {
        final String path = "/v1.24/containers/"+id+"/pause";

        Response response;
        RequestBody body = RequestBody.create(ContainerCreation.JSON,"");
        Request request = new Request.Builder()
                .url(URL+path)
                .post(body)
                .build();

        try {
            response = httpClient.newCall(request).execute();
            if(response.code() != 204 ) {
                throw new DockerServerException("Error pausing container with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unpause(String id) {
        final String path = "/v1.24/containers/" + id + "/unpause";

        Response response;
        RequestBody body = RequestBody.create(ContainerCreation.JSON, "");
        Request request = new Request.Builder()
                .url(URL + path)
                .post(body)
                .build();

        try {
            response = httpClient.newCall(request).execute();
            if (response.code() != 204) {
                throw new DockerServerException("Error unpausing container with path:" + URL + path + "\nMessage from Docker Daemon: " + response.body().string()
                        + "\nHTTP-Code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //##################################################################################################################

    private List<Container> runListCommand(String path) {
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
            String responseBody = response.body().string();
            logger.debug("Response: {}", responseBody);
            return containerListParse(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Container> containerListParse(String json) {
        try {
            Container[] containers = mapper.readValue(json, Container[].class);
            return Arrays.asList(containers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void removeContainer(String path, String id) {
        Response response;
        Request request = new Request.Builder()
                .url(URL+path)
                .delete()
                .build();

        try {
            response = httpClient.newCall(request).execute();
            if(response.code() == 304) {
                logger.warn("Container already removed: "+id);
            }
            else if(response.code() != 204 ) {
                throw new DockerServerException("Error removing container with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void killContainer(String path, String id) {
        Response response;
        RequestBody body = RequestBody.create(ContainerCreation.JSON,"");
        Request request = new Request.Builder()
                .url(URL+path)
                .post(body)
                .build();

        try {
            response = httpClient.newCall(request).execute();
            if(response.code() != 204 ) {
                throw new DockerServerException("Error killing container with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
