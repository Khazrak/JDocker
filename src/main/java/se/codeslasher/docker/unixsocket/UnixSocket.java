package se.codeslasher.docker.unixsocket;

import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Created by gesellix
 * From https://github.com/gesellix/docker-client
 * Modified by Khazrak (Groovy > Java)
 */
public class UnixSocket extends FileSocket {

    private static final Logger logger = LoggerFactory.getLogger(UnixSocket.class);
    private AFUNIXSocket socket;

    @Override
    public void connect(SocketAddress endpoint, int timeout) throws IOException {
        InetAddress address = ((InetSocketAddress) endpoint).getAddress();
        String socketPath = decodeHostname(address);

        logger.debug("connect via {}'...", socketPath);
        File socketFile = new File(socketPath);

        if (timeout < 0) {
            timeout = 0;
        }

        socket = AFUNIXSocket.newInstance();
        socket.connect(new AFUNIXSocketAddress(socketFile), timeout);
        socket.setSoTimeout(timeout);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    @Override
    public void bind(SocketAddress bindpoint) throws IOException {
        socket.bind(bindpoint);
    }

    @Override
    public boolean isConnected() {
        return socket.isConnected();
    }

    @Override
    public synchronized void close() throws IOException {
        socket.close();
    }

}
