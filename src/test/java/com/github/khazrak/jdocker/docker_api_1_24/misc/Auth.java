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
package com.github.khazrak.jdocker.docker_api_1_24.misc;

import com.github.khazrak.jdocker.DefaultDockerClient;
import com.github.khazrak.jdocker.DockerClient;
import com.github.khazrak.jdocker.model.api124.AuthTestResponse;
import com.github.khazrak.jdocker.model.api124.requests.AuthTestRequest;
import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class Auth {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(Auth.class);
    private final String user = "testuser";
    private final String password = "testpassword";

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
    public void auth() {
        final String path = "/v1.24%2Fauth";

        AuthTestRequest request = AuthTestRequest.builder()
                .username(user)
                .password(password)
                .serverAddress("test.se:5000")
                .build();

        AuthTestResponse response = client.auth(request);

        AuthTestResponse expected = AuthTestResponse.builder().status("Login Succeeded").build();

        assertThat(response).isEqualTo(expected);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void authToken() {
        final String path = "/v1.24%2Fauth";

        AuthTestRequest request = AuthTestRequest.builder()
                .username(user)
                .password(password)
                .serverAddress("test2.se:5000")
                .build();

        String token = "9cbaf023786cd7";

        AuthTestResponse response = client.auth(request);

        AuthTestResponse expected = AuthTestResponse.builder()
                .status("Login Succeeded")
                .identityToken(token)
                .build();

        assertThat(response).isEqualTo(expected);
        assertThat(response.getIdentityToken()).isEqualTo(token);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

}
