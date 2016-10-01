package se.codeslasher.docker.utils;

import okhttp3.HttpUrl;

import java.util.Map;

/**
 * Created by karl on 9/25/16.
 */
public interface URLResolver {

    HttpUrl resolve(String host, String path);
    HttpUrl resolve(String host, String path, Map<String, String> queries);

}
