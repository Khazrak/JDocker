package se.codeslasher.docker.utils;

import okhttp3.HttpUrl;
import se.codeslasher.docker.unixsocket.UnixSocketFactory;

import java.util.Map;

/**
 * Created by karl on 9/25/16.
 */
public class UnixURLResolver implements URLResolver {

    private UnixSocketFactory socketFactory;

    public UnixURLResolver(UnixSocketFactory socketFactory) {

        this.socketFactory = socketFactory;
    }

    @Override
    public HttpUrl resolve(String host, String path) {
        HttpUrl url = socketFactory.urlForUnixSocketPath(host, path);
        System.out.println(url);
        return url;
    }

    @Override
    public HttpUrl resolve(String host, String path, Map<String, String> queries) {
        HttpUrl url = socketFactory.urlForUnixSocketPath(host, path, queries);
        return url;
    }
}
