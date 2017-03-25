/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.khazrak.jdocker.utils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

import java.io.*;

public class RequestStreamBody extends RequestBody {

    private InputStream inputStream;

    public RequestStreamBody(InputStream inputStream) {
        if (inputStream == null) {
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

        try (BufferedInputStream bufIn = new BufferedInputStream(inputStream);
             BufferedOutputStream bufOut = new BufferedOutputStream(outputStream)) {

            int aByte;
            while ((aByte = bufIn.read()) != -1) {
                bufOut.write(aByte);
            }
            bufOut.flush();
        }
    }
}
