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

import com.github.khazrak.jdocker.unixsocket.NpipeSocketFactory;
import okhttp3.HttpUrl;

import java.util.Map;

public class NpipeURLResolver implements URLResolver {

    private NpipeSocketFactory socketFactory;

    public NpipeURLResolver(NpipeSocketFactory npipeSocketFactory) {
        this.socketFactory = npipeSocketFactory;
    }

    @Override
    public HttpUrl resolve(String host, String path) {
        HttpUrl url = socketFactory.urlForNamedPipeSocketPath(host, path);
        return url;
    }

    @Override
    public HttpUrl resolve(String host, String path, Map<String, String> queries) {
        HttpUrl url = socketFactory.urlForNamedPipeSocketPath(host, path, queries);
        return url;
    }
}
