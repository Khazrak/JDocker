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

import com.github.khazrak.jdocker.unixsocket.UnixSocketFactory;
import okhttp3.HttpUrl;

import java.util.Map;

public class UnixURLResolver implements URLResolver {

    private UnixSocketFactory socketFactory;

    public UnixURLResolver(UnixSocketFactory socketFactory) {

        this.socketFactory = socketFactory;
    }

    @Override
    public HttpUrl resolve(String host, String path) {
        HttpUrl url = socketFactory.urlForUnixSocketPath(host, path);
        return url;
    }

    @Override
    public HttpUrl resolve(String host, String path, Map<String, String> queries) {
        HttpUrl url = socketFactory.urlForUnixSocketPath(host, path, queries);
        return url;
    }
}
