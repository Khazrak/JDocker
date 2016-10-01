package se.codeslasher.docker.unixsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Created by gesellix
 * From https://github.com/gesellix/docker-client
 * Modified by Khazrak (Groovy > Java)
 */
public class NamedPipeSocket extends FileSocket {

    private static Logger logger = LoggerFactory.getLogger(NamedPipeSocket.class);

    private RandomAccessFile namedPipe;
    private boolean isClosed = false;

    @Override
    public void connect(SocketAddress endpoint, int timeout) throws IOException {
        InetAddress address = ((InetSocketAddress) endpoint).getAddress();
        String socketPath = decodeHostname(address);

        logger.debug("connect via '${socketPath}'...");

        socketPath = socketPath.replaceAll("/", "\\\\");
        this.namedPipe = new RandomAccessFile(socketPath, "rw");
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.namedPipe.getFD());
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(this.namedPipe.getFD());
    }

    @Override
    public boolean isClosed() {
        return this.isClosed;
    }

    @Override
    public synchronized void close() throws IOException {
        this.namedPipe.close();
        this.isClosed = true;
    }

}
