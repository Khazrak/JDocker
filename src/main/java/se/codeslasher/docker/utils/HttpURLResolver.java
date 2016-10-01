package se.codeslasher.docker.utils;

import okhttp3.HttpUrl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by karl on 9/25/16.
 */
public class HttpURLResolver implements URLResolver{
    @Override
    public HttpUrl resolve(String host, String path) {
        HttpUrl.Builder builder = HttpUrl.parse(host).newBuilder();
        return builder.addPathSegment(path).build();
    }

    @Override
    public HttpUrl resolve(String host, String path, Map<String, String> queries) {
        HttpUrl.Builder builder = HttpUrl.parse(host).newBuilder();
        builder = builder.addPathSegment(path);

        for(String key : queries.keySet()) {
            try {
                builder = builder.addEncodedQueryParameter(key, URLEncoder.encode(queries.get(key), StandardCharsets.UTF_8.toString()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return builder.build();
    }
}
