package se.codeslasher.docker.utils;

import okhttp3.HttpUrl;
import se.codeslasher.docker.unixsocket.NpipeSocketFactory;

import java.util.Map;

/**
 * Created by Khazrak on 2016-10-20.
 */
public class NpipeURLResolver implements URLResolver {

    private NpipeSocketFactory socketFactory;

    public NpipeURLResolver(NpipeSocketFactory npipeSocketFactory) {
        this.socketFactory = npipeSocketFactory;
    }

    @Override
    public HttpUrl resolve(String host, String path) {
        HttpUrl url = socketFactory.urlForNamedPipeSocketPath(host, path);
        return url;
    }

    @Override
    public HttpUrl resolve(String host, String path, Map<String, String> queries) {
        HttpUrl url = socketFactory.urlForNamedPipeSocketPath(host, path, queries);
        return url;
    }
}
