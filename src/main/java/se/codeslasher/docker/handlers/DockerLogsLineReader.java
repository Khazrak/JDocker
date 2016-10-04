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
package se.codeslasher.docker.handlers;

import org.slf4j.Logger;

import java.io.*;

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