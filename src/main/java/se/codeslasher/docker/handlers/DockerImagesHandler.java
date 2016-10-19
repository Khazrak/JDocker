/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.codeslasher.docker.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.model.api124.*;
import se.codeslasher.docker.model.api124.parameters.ListImagesParams;
import se.codeslasher.docker.model.api124.requests.BuildImageFromArchiveRequest;
import se.codeslasher.docker.model.api124.requests.BuildImageFromRemoteRequest;
import se.codeslasher.docker.utils.DockerImageName;
import se.codeslasher.docker.utils.RequestStreamBody;
import se.codeslasher.docker.utils.URLResolver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
        final String path = "v1.24/images/json";

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


    public InputStream pullImage(DockerImageName image) {
        return pull(image, getBase64EncodedJson("{}"));
    }

    public InputStream pullImage(DockerImageName image, AuthConfig authConfig) {
        try {
            String jsonHeader = mapper.writeValueAsString(authConfig);
            jsonHeader = getBase64EncodedJson(jsonHeader);
            return pull(image, jsonHeader);
        } catch (JsonProcessingException e) {
            logger.debug("Exception during oulling if image: " + image.toString() + " due to json serialization of authconfig", e);
        }
        return null;
    }

    public InputStream pullImage(DockerImageName image, String token) {
        String json = mapper.createObjectNode().put("identitytoken", token).toString();
        json = getBase64EncodedJson(json);

        return pull(image, json);
    }

    private InputStream pull(DockerImageName image, String encodedAuthJson) {
        final String path = "v1.24/images/create";

        Headers headers = new Headers.Builder()
                .add("Content-Type", "application/json")
                .add("X-Registry-Auth", encodedAuthJson)
                .build();

        Map<String, String> queries = new TreeMap<>();
        queries.put("fromImage", image.toStringWithoutTag());
        queries.put("tag", image.getTag());

        Response response = okHttpExecuter.post(headers, path, queries);
        return response.body().byteStream();
    }

    private String getBase64EncodedJson(String json) {
        return Base64.getEncoder().encodeToString(json.getBytes());
    }

    public Image inspectImage(DockerImageName imageName) {
        logger.debug("Inspecting image {}", imageName.toString());
        final String path;
        try {
            path = "v1.24/images/" + URLEncoder.encode(imageName.toString(), StandardCharsets.UTF_8.toString()) + "/json";
            Response response = okHttpExecuter.get(path);
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);

            return mapper.readValue(responseBody, Image.class);

        } catch (IOException e) {
            logger.error("Exception during inspecting image: " + imageName.toString(), e);
        }

        return null;
    }

    public void tagImage(DockerImageName original, DockerImageName newName) {
        logger.debug("Taggin image {} to {}", original, newName);
        final String path = "v1.24/images/" + original.toString() + "/tag";
        Map<String, String> queries = new TreeMap<>();
        queries.put("repo", newName.toStringWithoutTag());
        queries.put("tag", newName.getTag());
        okHttpExecuter.post(path, queries);
    }

    public InputStream pushImage(DockerImageName imageToPush, AuthConfig authConfig) {
        String auth = null;
        try {
            auth = getBase64EncodedJson(mapper.writeValueAsString(authConfig));
            return pushImageWithAuth(imageToPush, auth);
        } catch (JsonProcessingException e) {
            logger.error("Exception during push of image due to Json serialization of auth problem", e);
        }
        return null;
    }

    public InputStream pushImage(DockerImageName imageToPush, String identyToken) {
        String token = mapper.createObjectNode().put("identitytoken", identyToken).toString();
        token = getBase64EncodedJson(token);
        return pushImageWithAuth(imageToPush, token);
    }

    private InputStream pushImageWithAuth(DockerImageName name, String auth) {
        logger.debug("Pushing image {}", name);
        final String path = "v1.24/images/" + name.toStringWithoutTag() + "/push";
        Headers headers = new Headers.Builder().add("X-Registry-Auth", auth).build();
        Map<String, String> queries = new TreeMap<>();
        queries.put("tag", name.getTag());

        Response response = okHttpExecuter.post(headers, path, queries);

        return response.body().byteStream();
    }

    public String removeImage(DockerImageName name, boolean force, boolean noprune) {
        logger.debug("Removing image {}", name);
        final String path = "v1.24/images/" + name;
        Map<String, String> queries = new TreeMap<>();
        queries.put("force", Boolean.toString(force));
        queries.put("noprune", Boolean.toString(noprune));
        Response response = okHttpExecuter.delete(path, queries);
        String responseBody = null;
        try {
            responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);
        } catch (IOException e) {
            logger.error("Exception during removal of image " + name, e);
        }

        return responseBody;
    }

    public List<ImageSearchInfo> searchImage(String term) {
        logger.debug("Searching docker hub for {}", term);
        final String path = "v1.24/images/search";
        Map<String, String> queries = new TreeMap<>();
        queries.put("term", term);
        Response response = okHttpExecuter.get(path, queries);

        try {
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);
            ImageSearchInfo[] searchInfos = mapper.readValue(responseBody, ImageSearchInfo[].class);
            return Arrays.asList(searchInfos);
        } catch (IOException e) {
            logger.error("Exception during image search for term " + term, e);
        }

        return null;
    }

    public List<ImageHistoryInfo> historyOfImage(DockerImageName name) {
        logger.debug("Retrieving history of image {}", name);
        final String path = "v1.24/images/" + name + "/history";

        try {
            Response response = okHttpExecuter.get(path);
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);
            ImageHistoryInfo[] history = mapper.readValue(responseBody, ImageHistoryInfo[].class);
            return Arrays.asList(history);
        } catch (IOException e) {
            logger.error("Exception during retrieving of history for image " + name, e);
        }

        return null;
    }

    public InputStream buildImageFromArchive(BuildImageFromArchiveRequest request) {
        logger.debug("Building image from archive");
        final String path = "v1.24/build";
        Map<String, String> queries = request.getQueries();

        try {
            Headers headers = new Headers.Builder()
                    .add("X-Registry-Config", getBase64EncodedJson(mapper.writeValueAsString(request.getAuthConfigs())))
                    .add("Content-type", "application/tar")
                    .build();

            Response response = okHttpExecuter.post(headers,path,queries, request.getBody());
            return response.body().byteStream();
        } catch (IOException e) {
            logger.error("Exception during build from archive", e);
        }

        return null;
    }

    public InputStream buildImageFromRemote(BuildImageFromRemoteRequest request) {
        logger.debug("Building image from remote url: {}", request.getRemoteUrl());
        final String path = "v1.24/build";
        Map<String, String> queries = request.getQueries();

        try {
            Headers headers = new Headers.Builder()
                    .add("X-Registry-Config", getBase64EncodedJson(mapper.writeValueAsString(request.getAuthConfigs())))
                    .add("Content-type", "application/tar")
                    .build();

            Response response = okHttpExecuter.post(headers,path,queries);
            return response.body().byteStream();
        } catch (IOException e) {
            logger.error("Exception during build from remote", e);
        }

        return null;
    }

    public InputStream getImageTar(DockerImageName repositoryName) {
        logger.debug("Downloading images as tar from repository: {}", repositoryName.toString());
        final String path = "v1.24/images/get";
        String name = repositoryName.getImageName();

        if(repositoryName.getTag() != null) {
            name += ":" + repositoryName.getTag();
        }

        if(repositoryName.getImageRepo() != null) {
            name = repositoryName.getImageRepo();
        }

        Map<String, String> queries = new TreeMap<>();
        queries.put("names", name);


        Response response = okHttpExecuter.get(path, queries);

        return response.body().byteStream();
    }

    public String importImageTar(InputStream input, boolean quiet) {
        logger.debug("Importing images from tar");
        final String path = "v1.24/images/load";
        Map<String,String> queries = new TreeMap<>();
        queries.put("quiet", Boolean.toString(quiet));
        RequestStreamBody body = new RequestStreamBody(input);
        Headers headers = new Headers.Builder().add("Content-Type", "application/x-tar").build();
        Response response = okHttpExecuter.post(headers, path, queries, body);
        try {
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);
            return responseBody;
        } catch (IOException e) {
            logger.error("Exception during retrieving body from response", e);
        }
        return null;
    }
}
