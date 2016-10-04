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
import se.codeslasher.docker.utils.DockerImageName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class ImagePush {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(ImagePush.class);
    private final String USER = "testuser";
    private final String PASSWORD = "testpassword";

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
    public void push() {

        final String path = "/v1.24%2Fimages%2Fmyrepo.se:5000%2Fbusybox%2Fpush?tag=latest";

        DockerImageName imageToPush = new DockerImageName("myrepo.se:5000/busybox");

        AuthConfig authConfig = AuthConfig.builder().username(USER).password(PASSWORD).email("test@test.com").build();

        InputStream input = client.pushImage(imageToPush, authConfig);

        List<String> lines = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(lines.get(lines.size()-1)).isEqualTo("{\"progressDetail\":{},\"aux\":{\"Tag\":\"latest\",\"Digest\":\"sha256:540f2e917216c5cfdf047b246d6b5883932f13d7b77227f09e03d42021e98941\",\"Size\":527}}");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

}
