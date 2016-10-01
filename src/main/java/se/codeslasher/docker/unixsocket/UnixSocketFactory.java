package se.codeslasher.docker.unixsocket;

import okhttp3.HttpUrl;
import org.newsclub.net.unix.AFUNIXSocket;

import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by gesellix
 * From https://github.com/gesellix/docker-client
 * Modified by Khazrak (Groovy > Java)
 */
public class UnixSocketFactory extends FileSocketFactory {

    public static boolean isSupported() {
        boolean isWindows = false;
        try {
            String property = System.getProperty("os.name");
            if (property != null) {
                isWindows = property.toLowerCase().contains("windows");
            }
        } catch (Exception e) {

        }
        return !isWindows && AFUNIXSocket.isSupported();
    }

    public HttpUrl urlForUnixSocketPath(String unixSocketPath, String path) {
        return new HttpUrl.Builder()
                .scheme("http")
                .host(UnixSocket.encodeHostname(unixSocketPath))
                .addPathSegment(path)
                .build();
    }

    public HttpUrl urlForUnixSocketPath(String unixSocketPath, String path, Map<String, String> queries) {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder = builder
                .scheme("http")
                .host(UnixSocket.encodeHostname(unixSocketPath))
                .addPathSegment(path);

        for(String key : queries.keySet()) {
            try {
                builder = builder.addEncodedQueryParameter(key, URLEncoder.encode(queries.get(key), StandardCharsets.UTF_8.toString()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return builder.build();
    }

    public Socket createSocket() {
        return new UnixSocket();
    }

}
