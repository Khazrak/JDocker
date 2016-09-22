package se.codeslasher.docker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.exception.DockerServerException;
import se.codeslasher.docker.handlers.*;
import se.codeslasher.docker.model.api124.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * Created by karl on 9/5/16.
 */
public class DefaultDockerClient implements DockerClient {

    private static Logger logger = LoggerFactory.getLogger(DefaultDockerClient.class);

    private OkHttpClient httpClient;

    private final String URL;

    private DockerLogsHandler logsHandler;
    private DockerPullHandler pullHandler;

    private ObjectMapper mapper;

    public DefaultDockerClient() {
        httpClient = new OkHttpClient();
        URL = "http://127.0.0.1:9779";
        mapper = new ObjectMapper();



        logsHandler = new DockerLogsHandler(httpClient, URL);
        pullHandler = new DockerPullHandler(httpClient, mapper, URL);
    }

    public DefaultDockerClient(String host) {
        httpClient = new OkHttpClient();
        URL = host;
        mapper = new ObjectMapper();

        logsHandler = new DockerLogsHandler(httpClient, URL);
        pullHandler = new DockerPullHandler(httpClient, mapper, URL);
    }

    public void close()  {
        httpClient = null;
    }

    @Override
    public String createContainer(ContainerCreation spec) {
        String id = null;

        Response response = null;
        try {
            String json = mapper.writeValueAsString(spec);

            RequestBody body = RequestBody.create(ContainerCreation.JSON,json);

            Request request = new Request.Builder()
                    .url(URL+ContainerCreation.PATH)
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

    @Override
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

    @Override
    public ContainerProcesses top(String id) {
        return top(id,null);
    }

    @Override
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
            System.out.println(response.code());
            //System.out.println(response.body().string());
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

    @Override
    public List<ContainerFileSystemChange> containerFileSystemChanges(String id) {
        final String path = "/v1.24/containers/"+id+"/changes";

        Response response;
        Request request = new Request.Builder()
                .url(URL+path)
                .get()
                .build();

        try {
            response = httpClient.newCall(request).execute();
            System.out.println(response.code());
            //System.out.println(response.body().string());
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

    @Override
    public ContainerStats stats(String id) {
        final String path = "/v1.24/containers/"+id+"/stats?stream=false";

        Response response;
        Request request = new Request.Builder()
                .url(URL+path)
                .get()
                .build();

        try {
            response = httpClient.newCall(request).execute();
            System.out.println(response.code());
            //System.out.println(response.body().string());
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

    @Override
    public InputStream statsStream(String id) {
        final String path = "/v1.24/containers/"+id+"/stats?stream=true";

        Response response;
        Request request = new Request.Builder()
                .url(URL+path)
                .get()
                .build();

        try {
            response = httpClient.newCall(request).execute();
            System.out.println(response.code());
            //System.out.println(response.body().string());
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

    @Override
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

    @Override
    public void stop(String id) {
        stop(id,10);
    }

    @Override
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

    @Override
    public void remove(String id) {
        final String path = "/v1.24/containers/"+id;
        removeContainer(path, id);
    }

    @Override
    public void remove(String id, boolean forceRemove, boolean removeVolume) {
        final String path = "/v1.24/containers/"+id+"?force="+forceRemove+"&v="+removeVolume;
        removeContainer(path, id);
    }

    @Override
    public void kill(String id) {
        final String path = "/v1.24/containers/"+id+"/kill";
        killContainer(path,id);
    }

    @Override
    public void kill(String id, String signal) {
        final String path = "/v1.24/containers/"+id+"/kill?signal="+signal;
        killContainer(path,id);
    }

    @Override
    public List<Container> list() {
        final String path = "/v1.24/containers/json";
        return runListCommand(path);
    }

    @Override
    public List<Container> list(ContainerListRequest listRequest) {

        String params = listRequest.toString();
        if(params.length() > 0) {
            return runListCommand("/v1.24/containers/json?"+params);
        }
        else {
            return list();
        }
    }

    @Override
    public List<String> logs(String id, DockerLogsParameters params) {
        return logsHandler.logs(id, params);
    }

    @Override
    public DockerLogsLineReader logsSpecial(String id, DockerLogsParameters params) {
        return logsHandler.logsSpecial(id, params);
    }

    @Override
    public InputStream logsRawStream(String id, DockerLogsParameters params) {
        return logsHandler.logsRawStream(id, params);
    }

    @Override
    public String pull(DockerImageName image) {
        return pullHandler.pull(image);
    }

    @Override
    public void pull(DockerImageName image, AuthConfig authConfig) {
        pullHandler.pull(image, authConfig);
    }

    @Override
    public void pull(DockerImageName image, String token) {
        pullHandler.pull(image,token);
    }

    @Override
    public InputStream logsStream(String id, DockerLogsParameters params) {
        final String path = "/v1.24/containers/"+id+"/logs"+params.toString();
        Response response;
        Request request = new Request.Builder()
                .url(URL+path)
                .get()
                .build();

        try {
            response = httpClient.newCall(request).execute();
            System.out.println(response.code());
            //System.out.println(response.body().string());
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

            return containerListParse(response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Container> containerListParse(String json) {


        System.out.println(json);


        try {
            return mapper.readValue(json, new TypeReference<List<Container>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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

}
