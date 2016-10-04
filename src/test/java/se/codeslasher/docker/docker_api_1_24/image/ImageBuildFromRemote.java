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
package se.codeslasher.docker.docker_api_1_24.image;

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
import se.codeslasher.docker.DefaultDockerClient;
import se.codeslasher.docker.DockerClient;
import se.codeslasher.docker.model.api124.AuthConfig;
import se.codeslasher.docker.model.api124.requests.BuildImageFromArchiveRequest;
import se.codeslasher.docker.model.api124.requests.BuildImageFromRemoteRequest;
import se.codeslasher.docker.utils.DockerImageName;
import se.codeslasher.docker.utils.RequestStreamBody;

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

public class ImageBuildFromRemote {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(ImageBuildFromRemote.class);

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

        final String path = "/v1.24%2Fbuild?labels=%7B%22LICENSE%22%3A%22GPL%22%7D&pull=true&remote=https%3A%2F%2Fgithub.com%2FSylvainLasnier%2Fecho.git&rm=true&t=busy-echo%3Alatest";

        DockerImageName name = new DockerImageName("busy-echo");

        AuthConfig authConfig = AuthConfig.builder().username("testuser").password("testpassword").build();

        BuildImageFromRemoteRequest request = BuildImageFromRemoteRequest.builder()
                .authConfig("myrepo.se:5000",authConfig)
                .remoteUrl("https://github.com/SylvainLasnier/echo.git")
                .tag(name)
                .removeIntermediateContainers(true)
                .label("LICENSE","GPL")
                .pull(true)
                .build();

        InputStream inputStream = client.buildImageFromRemote(request);

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


        assertThat(lines.get(lines.size() - 1)).contains("{\"stream\":\"Successfully built e9ae56ab288b\\n\"}");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);

    }

}
