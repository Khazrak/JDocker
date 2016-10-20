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
package com.github.khazrak.jdocker.docker_api_1_24.container;

import com.github.khazrak.jdocker.DockerClient;
import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import com.github.khazrak.jdocker.DefaultDockerClient;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class ContainerStop {

    private DockerClient client;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().usingFilesUnderClasspath("src/test/resources/1_24").port(9779)); // No-args constructor defaults

    @Before
    public void setup() {
        client = new DefaultDockerClient("http://127.0.0.1:9779");
    }

    @After
    public void tearDown() {
        client.close();
    }

    @Test
    public void stop() {
        final String path = "/v1.24%2Fcontainers%2Ff2aca7ccb724d73aad6e4f6%2Fstop?t=10";

        client.stop("f2aca7ccb724d73aad6e4f6");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null, null, null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);
        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void stopNoneExisting() {
        final String path = "/v1.24%2Fcontainers%2Fnone_existing%2Fstop?t=20";
        //Will log 304
        client.stop("none_existing",20);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null, null, null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }
}
