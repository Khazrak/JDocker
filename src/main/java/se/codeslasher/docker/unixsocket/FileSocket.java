package se.codeslasher.docker.unixsocket;

import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by gesellix
 * From https://github.com/gesellix/docker-client
 * Modified by Khazrak (Groovy > Java)
 */
abstract class FileSocket extends Socket {

    static final String SOCKET_MARKER = ".socket";

    static String encodeHostname(String hostname) {
        return HostnameEncoder.encode(hostname) + SOCKET_MARKER;
    }

    static String decodeHostname(InetAddress address) {
        String hostName = address.getHostName();
        return HostnameEncoder.decode(hostName.substring(0, hostName.indexOf(SOCKET_MARKER)));
    }
}
