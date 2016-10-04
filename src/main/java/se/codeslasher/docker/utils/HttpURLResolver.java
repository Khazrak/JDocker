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
package se.codeslasher.docker.utils;

import okhttp3.HttpUrl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpURLResolver implements URLResolver{
    @Override
    public HttpUrl resolve(String host, String path) {
        HttpUrl.Builder builder = HttpUrl.parse(host).newBuilder();
        return builder.addPathSegment(path).build();
    }

    @Override
    public HttpUrl resolve(String host, String path, Map<String, String> queries) {
        HttpUrl.Builder builder = HttpUrl.parse(host).newBuilder();
        builder = builder.addPathSegment(path);

        for(String key : queries.keySet()) {
            try {
                builder = builder.addEncodedQueryParameter(key, URLEncoder.encode(queries.get(key), StandardCharsets.UTF_8.toString()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return builder.build();
    }
}
