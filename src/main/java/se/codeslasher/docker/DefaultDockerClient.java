package se.codeslasher.docker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.exception.DockerServerException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by karl on 9/5/16.
 */
public class DefaultDockerClient implements DockerClient {

    private static Logger logger = LoggerFactory.getLogger(DefaultDockerClient.class);

    private OkHttpClient httpClient;

    private final String URL;

    private ObjectMapper mapper;

    public DefaultDockerClient() {
        httpClient = new OkHttpClient();
        URL = "http://127.0.0.1:9779";
        mapper = new ObjectMapper();
    }

    public DefaultDockerClient(String host) {
        httpClient = new OkHttpClient();
        URL = host;
        mapper = new ObjectMapper();
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
        List<String> logLines = null;
        final String path = "/v1.24/containers/"+id+"/logs"+params.toString();
        logger.info((path));
        Response response;
        Request request = new Request.Builder()
                .url(URL+path)
                .get()
                .build();

        try {
            response = httpClient.newCall(request).execute();
            System.out.println(response.code());
            //System.out.println(response.body().string());

            try(BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream()))) {
                logLines = new ArrayList<>();
                String line = "";
                while((line = reader.readLine()) != null) {
                    logLines.add(line);
                }

            }

            if(response.code() != 200 ) {
                throw new DockerServerException("Error stopping container with path:" + URL+path + "\nMessage from Docker Daemon: " +response.body().string()
                        +"\nHTTP-Code: "+response.code());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return logLines;
    }

    @Override
    public DockerLogsLineReader logsSpecial(String id, DockerLogsParameters params) {
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
            return new DockerLogsLineReader(response.body().byteStream());



        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String pull(DockerImageName image) {
        final String path = "/v1.24/images/create?fromImage="+image.toStringWithoutTag() + "&tag=" + image.getTag();
        RequestBody body = RequestBody.create(ContainerCreation.JSON,"");
        Response response;


        Map<String, String> headersMap = new TreeMap<>();
        headersMap.put("Content-Type","application/json");
        headersMap.put("X-Registry-Auth", "");
        Headers headers = Headers.of(headersMap);

        Request request = new Request.Builder()
                .headers(headers)
                .url(URL+path)
                .post(body)
                .build();

        try {
            response = httpClient.newCall(request).execute();
            return response.body().string();
        }
        catch (IOException e) {
            logger.info(e.getLocalizedMessage(),e);
        }

        return null;

    }

    @Override
    public void pull(DockerImageName image, AuthConfig authConfig) {
        final String path = "/v1.24/images/create?fromImage="+image.toStringWithoutTag() + "?tag=" + image.getTag();

        String jsonHeader = null;
        RequestBody body = RequestBody.create(ContainerCreation.JSON,"");
        try {
            jsonHeader = mapper.writeValueAsString(authConfig);
            jsonHeader = Base64.getEncoder().encodeToString(jsonHeader.getBytes());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Response response;
        Request request = new Request.Builder()
                .header("X-Registry-Auth", jsonHeader)
                .url(URL+path)
                .post(body)
                .build();
    }

    @Override
    public void pull(DockerImageName image, String token) {

        final String path = "/v1.24/images/create?fromImage="+image.toStringWithoutTag() + "?tag=" + image.getTag();
        RequestBody body = RequestBody.create(ContainerCreation.JSON,"");
        TreeMap<String, String> map = new TreeMap<>();

        map.put("registrytoken",token);

        String jsonHeader = null;
        try {
            jsonHeader = mapper.writeValueAsString(map);
            jsonHeader = Base64.getEncoder().encodeToString(jsonHeader.getBytes());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Response response;
        Request request = new Request.Builder()
                .header("X-Registry-Auth", jsonHeader)
                .url(URL+path)
                .post(body)
                .build();


    }

    @Override
    public InputStream logsRawStream(String id, DockerLogsParameters params) {
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
            return response.body().byteStream();



        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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
