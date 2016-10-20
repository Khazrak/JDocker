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
package com.github.khazrak.jdocker.docker_api_1_24.image;

import com.github.khazrak.jdocker.DockerClient;
import com.github.khazrak.jdocker.model.api124.AuthConfig;
import com.github.khazrak.jdocker.utils.DockerImageName;
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
import com.github.khazrak.jdocker.DefaultDockerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class ImagePull {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(ImagePull.class);
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
    public void pull() {
        final String path = "/v1.24%2Fimages%2Fcreate?fromImage=busybox&tag=latest";


        DockerImageName image = new DockerImageName("busybox");
        InputStream input = client.pullImage(image);

        List<String> lines = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
                logger.info(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(lines.get(lines.size()-1)).contains("{\"status\":\"Status: Downloaded newer image for busybox:latest\"}");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void pullAuth() {
        final String path = "/v1.24%2Fimages%2Fcreate?fromImage=codeslasher.se%3A5000%2Fmongo&tag=latest";


        DockerImageName image = new DockerImageName("codeslasher.se:5000/mongo");
        AuthConfig authConfig = AuthConfig.builder().username(user).password(password).email("test@test.se").build();
        InputStream input = client.pullImage(image, authConfig);

        List<String> lines = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
                logger.info(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(lines.get(lines.size()-1)).contains("{\"status\":\"Status: Downloaded newer image for codeslasher.se:5000/mongo:latest\"}");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    //@Test
    public void pullToken() {
        //TODO: private Registry with token
    }

    @Test
    public void pullUbuntu() {
        final String path = "/v1.24%2Fimages%2Fcreate?fromImage=ubuntu&tag=16.04";
        DockerImageName image = new DockerImageName("ubuntu:16.04");
        InputStream input = client.pullImage(image);

        List<String> lines = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
                logger.info(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(lines.get(lines.size()-1)).contains("{\"status\":\"Status: Downloaded newer image for ubuntu:16.04\"}");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

}
