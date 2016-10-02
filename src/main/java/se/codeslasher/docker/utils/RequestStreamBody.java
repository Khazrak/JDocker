package se.codeslasher.docker.utils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

import java.io.*;

/**
 * Created by karl on 10/2/16.
 */
public class RequestStreamBody extends RequestBody {

    private InputStream inputStream;

    public RequestStreamBody(InputStream inputStream) {
        if(inputStream == null) {
            throw new NullPointerException("InputStream in RequestStreamBody creation may not be null!");
        }

        this.inputStream = inputStream;
    }

    @Override
    public MediaType contentType() {
        return MediaType.parse("application/tar");
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        OutputStream outputStream = sink.outputStream();

        try(BufferedInputStream bufIn = new BufferedInputStream(inputStream);
            BufferedOutputStream bufOut = new BufferedOutputStream(outputStream)) {

            int aByte;
            while((aByte = bufIn.read()) != -1) {
                bufOut.write(aByte);
            }
            bufOut.flush();
        }
    }
}
