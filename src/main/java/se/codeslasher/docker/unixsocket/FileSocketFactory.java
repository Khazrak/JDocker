package se.codeslasher.docker.unixsocket;

import okhttp3.Dns;

import javax.net.SocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gesellix
 * From https://github.com/gesellix/docker-client
 * Modified by Khazrak (Groovy > Java)
 */
public class FileSocketFactory extends SocketFactory implements Dns {

    @Override
    public Socket createSocket(String s, int i) throws IOException, UnknownHostException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Socket createSocket(String s, int i, InetAddress inetAddress, int i1) throws IOException, UnknownHostException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress1, int i1) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        if(hostname.endsWith(FileSocket.SOCKET_MARKER)) {
            return Arrays.asList(InetAddress.getByAddress(hostname, new byte[]{0,0,0,0}));
        }
        else {
            return SYSTEM.lookup(hostname);
        }
    }
}
