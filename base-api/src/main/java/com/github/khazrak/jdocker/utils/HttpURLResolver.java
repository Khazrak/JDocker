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

import okhttp3.HttpUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpURLResolver implements URLResolver {

    private Logger logger = LoggerFactory.getLogger(HttpURLResolver.class);

    @Override
    public HttpUrl resolve(String host, String path) {
        HttpUrl.Builder builder = HttpUrl.parse(host).newBuilder();
        return builder.addPathSegment(path).build();
    }

    @Override
    public HttpUrl resolve(String host, String path, Map<String, String> queries) {
        HttpUrl.Builder builder = HttpUrl.parse(host).newBuilder();
        builder = builder.addPathSegment(path);

        for (Map.Entry<String, String> entry: queries.entrySet()) {
            try {
                builder = builder.addEncodedQueryParameter(entry.getKey(), URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            } catch (UnsupportedEncodingException e) {
                logger.error("HttpURLResolver failed to encode key: " + entry.getKey() + " and value: " + entry.getValue(), e);
            }
        }

        return builder.build();
    }
}
