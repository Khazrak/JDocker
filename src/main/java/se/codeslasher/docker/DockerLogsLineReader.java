package se.codeslasher.docker;

import org.slf4j.Logger;

import java.io.*;

/**
 * Created by karl on 9/15/16.
 */
public class DockerLogsLineReader implements AutoCloseable {

    public static int STDIN = 1;
    public static int STDOUT = 2;
    public static int STDERR = 3;

    private static Logger logger;
    private BufferedReader reader;

    DockerLogsLineReader(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String readLine() throws IOException {

        String data = reader.readLine();
        if(data == null) {
            return null;
        }

        int streamBit = data.charAt(0);

        String res = "";

        if(streamBit == STDIN) {
            res += "<STDIN>";
        }
        else if(streamBit == STDOUT) {
            res += "<STDOUT>";
        }
        else if(streamBit == STDERR) {
            res += "<SRDERR>";
        }

        res += " " + data.substring(8);
        return res;
    }


    @Override
    public void close() throws IOException {
        if(reader != null) {
            reader.close();
        }
    }
}