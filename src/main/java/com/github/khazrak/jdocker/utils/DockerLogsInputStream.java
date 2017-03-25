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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DockerLogsInputStream extends InputStream {

    public enum LOG_TYPE{
        NONE(-1), STDIN(0), STDOUT(1), STDERR(2);

        int header;

        private LOG_TYPE(int headerByte) {
            header = headerByte;
        }

        public static LOG_TYPE valueOf(int value) {
            if(value == 0) {
                return STDIN;
            }
            else if(value == 1) {
                return STDOUT;
            }
            else if(value == 2) {
                return STDERR;
            }
            return NONE;

        }
    }

    private BufferedReader reader;

    private int index;
    private String cache;
    private LOG_TYPE currentLogType;

    public DockerLogsInputStream(InputStream inputStream) throws IOException {
        reader = new BufferedReader(new InputStreamReader(inputStream));
        cache = reader.readLine(); //initial read
        index = 8;
        currentLogType = LOG_TYPE.NONE;
    }

    @Override
    public int read() throws IOException {
        int value = -1;

        if(cache != null && index >= cache.length()) {
            cache = reader.readLine();
            index = 8;
            currentLogType = parseLogType(cache);
            value = (int)'\n';
        }
        else if(cache != null) {
            value = cache.charAt(index);
            index++;
        }

        return value;
    }

    /**
     * Returns the current lines LOG_TYPE
     *
     * This will not work with BufferedReader since it buffers Several lines
     *
     * @return
     */
    public LOG_TYPE getCurrentLineLogType() {
        return currentLogType;
    }

    @Override
    public void close() throws IOException {
        super.close();
        reader.close();
    }

    public static LOG_TYPE parseLogType(String line) {
        if(line == null || line.length() == 0) {
            return LOG_TYPE.NONE;
        }
        return LOG_TYPE.valueOf(line.charAt(0));
    }
}
