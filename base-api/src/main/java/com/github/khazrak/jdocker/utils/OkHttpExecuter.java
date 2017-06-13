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
package com.github.khazrak.jdocker.utils;

import com.github.khazrak.jdocker.exception.DockerClientException;
import com.github.khazrak.jdocker.exception.DockerServerException;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class OkHttpExecuter {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final Logger logger = LoggerFactory.getLogger(OkHttpExecuter.class);


    private RequestBody emptyRequestBody;
    private OkHttpClient httpClient;

    private String url;
    private URLResolver urlResolver;

    public OkHttpExecuter(OkHttpClient httpClient, String url, URLResolver urlResolver) {
        this.httpClient = httpClient;
        this.url = url;
        this.urlResolver = urlResolver;

        emptyRequestBody = RequestBody.create(JSON, "");
    }

    //=== GET ==========================================================================================================

    public Response get(String path) {
        Request request = new Request.Builder()
                .url(urlResolver.resolve(url, path))
                .get()
                .build();

        return execute(request);
    }

    public Response get(String path, Map<String, String> queries) {
        Request request = new Request.Builder()
                .url(urlResolver.resolve(url, path, queries))
                .get()
                .build();

        return execute(request);
    }

    //=== POST =========================================================================================================

    public Response post(String path) {
        Request request = new Request.Builder()
                .url(urlResolver.resolve(url, path))
                .post(emptyRequestBody)
                .build();

        return execute(request);
    }

    public Response post(Headers headers, String path) {
        Request request = new Request.Builder()
                .headers(headers)
                .url(urlResolver.resolve(url, path))
                .post(emptyRequestBody)
                .build();

        return execute(request);
    }

    public Response post(String path, Map<String, String> queries) {
        Request request = new Request.Builder()
                .url(urlResolver.resolve(url, path, queries))
                .post(emptyRequestBody)
                .build();

        return execute(request);
    }

    public Response post(Headers headers, String path, Map<String, String> queries) {
        Request request = new Request.Builder()
                .headers(headers)
                .url(urlResolver.resolve(url, path, queries))
                .post(emptyRequestBody)
                .build();

        return execute(request);
    }

    public Response post(String path, String jsonBody) {
        RequestBody body = RequestBody.create(JSON, jsonBody);
        Request request = new Request.Builder()
                .url(urlResolver.resolve(url, path))
                .post(body)
                .build();

        return execute(request);
    }

    public Response post(String path, Map<String, String> queries, String jsonBody) {
        RequestBody body = RequestBody.create(JSON, jsonBody);
        Request request = new Request.Builder()
                .url(urlResolver.resolve(url, path, queries))
                .post(body)
                .build();

        return execute(request);
    }

    public Response post(Headers headers, String path, Map<String, String> queries, RequestStreamBody body) {
        Request request = new Request.Builder()
                .headers(headers)
                .url(urlResolver.resolve(url, path, queries))
                .post(body)
                .build();

        return execute(request);
    }

    //=== DELETE =======================================================================================================

    public Response delete(String path) {
        Request request = new Request
                .Builder()
                .url(urlResolver.resolve(url, path))
                .delete()
                .build();

        return execute(request);
    }

    public Response delete(String path, Map<String, String> queries) {
        Request request = new Request
                .Builder()
                .url(urlResolver.resolve(url, path, queries))
                .delete()
                .build();

        return execute(request);
    }

    public Response delete(String path, String jsonBody) {
        RequestBody body = RequestBody.create(JSON, jsonBody);

        Request request = new Request
                .Builder()
                .url(urlResolver.resolve(url, path))
                .delete(body)
                .build();

        return execute(request);
    }

    public Response delete(String path, Map<String, String> queries, String jsonBody) {
        RequestBody body = RequestBody.create(JSON, jsonBody);
        Request request = new Request
                .Builder()
                .url(urlResolver.resolve(url, path, queries))
                .delete(body)
                .build();

        return execute(request);

    }

    private Response execute(Request request) {
        Response response = null;
        try {
            HttpUrl url = request.url();
            logger.debug("url {}", url);

            response = this.httpClient.newCall(request).execute();

            int code = response.code();
            if (response.code() >= 300 && response.code() < 400) {
                logger.warn("Http Code: {} while doing command: {}", code, url);
            } else if (response.code() >= 400 && response.code() < 500) {
                throw new DockerClientException("Command with url: {}" + url + " Failed\nMessage from Docker Daemon: " + response.body().string()
                        + "\nHTTP-Code: " + code);
            } else if (response.code() >= 500) {
                throw new DockerServerException("Command with url: " + url + " Failed\nMessage from Docker Daemon: " + response.body().string()
                        + "\nHTTP-Code: " + code);
            }

        } catch (IOException e) {
            logger.error("IOException during execute in OkHttpExecuter", e);
        }

        return response;
    }


    public Response head(String path, Map<String, String> queries) {
        Request request = new Request
                .Builder()
                .url(urlResolver.resolve(url, path, queries))
                .head()
                .build();

        return execute(request);
    }

    public Response put(String path, Map<String, String> queries, RequestStreamBody body) {
        Request request = new Request
                .Builder()
                .url(urlResolver.resolve(url, path, queries))
                .put(body)
                .build();

        return execute(request);
    }
}
