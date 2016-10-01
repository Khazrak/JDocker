package se.codeslasher.docker.unixsocket;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by gesellix
 * From https://github.com/gesellix/docker-client
 * Modified by Khazrak (Groovy > Java)
 */
public class NpipeSocketFactory extends FileSocketFactory {

    @Override
    public Socket createSocket() throws IOException {
        return new NamedPipeSocket();
    }

}
