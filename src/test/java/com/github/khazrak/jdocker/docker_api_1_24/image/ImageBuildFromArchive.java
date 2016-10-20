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
import com.github.khazrak.jdocker.DockerClient;
import com.github.khazrak.jdocker.model.api124.AuthConfig;
import com.github.khazrak.jdocker.model.api124.requests.BuildImageFromArchiveRequest;
import com.github.khazrak.jdocker.utils.DockerImageName;
import com.github.khazrak.jdocker.utils.RequestStreamBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageBuildFromArchive {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(ImageBuildFromArchive.class);

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
    public void build() {

        final String path = "/v1.24%2Fbuild?pull=true&rm=true&t=test_buntu%3A1.0";

        DockerImageName name = new DockerImageName("test_buntu:1.0");

        InputStream input = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Path filePath = Paths.get(classLoader.getResource("to_build.tar.gz").toURI());
            input = Files.newInputStream(filePath, StandardOpenOption.READ);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestStreamBody body = new RequestStreamBody(input);

        AuthConfig authConfig = AuthConfig.builder().username("testuser").password("testpassword").build();

        BuildImageFromArchiveRequest request = BuildImageFromArchiveRequest.builder()
                .authConfig("myrepo.se:5000",authConfig)
                .tag(name)
                .body(body)
                .removeIntermediateContainers(true)
                .pull(true)
                .build();

        InputStream inputStream = client.buildImageFromArchive(request);

        List<String> lines = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
                logger.info(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        assertThat(lines.get(lines.size() - 1)).contains("{\"stream\":\"Successfully built 249496a881e2\\n\"}");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);

    }

    @Test
    public void buildWithRemoteRegistry() {

        final String path = "/v1.24%2Fbuild?pull=true&rm=true&t=test_buntu%3A2.0";

        DockerImageName name = new DockerImageName("test_buntu:2.0");

        InputStream input = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Path filePath = Paths.get(classLoader.getResource("to_build2.tar.gz").toURI());
            input = Files.newInputStream(filePath, StandardOpenOption.READ);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestStreamBody body = new RequestStreamBody(input);

        AuthConfig authConfig = AuthConfig.builder().username("testuser").password("testpassword").build();

        BuildImageFromArchiveRequest request = BuildImageFromArchiveRequest.builder()
                .authConfig("codeslasher.se:5000", authConfig)
                .tag(name)
                .body(body)
                .removeIntermediateContainers(true)
                .pull(true)
                .build();

        InputStream inputStream = client.buildImageFromArchive(request);

        List<String> lines = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
                logger.info(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(lines.get(lines.size() - 1)).contains("{\"stream\":\"Successfully built 0918942c704b\\n\"}");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null, null, null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST, pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

}
