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
package se.codeslasher.docker.docker_api_1_24.exec;

import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import se.codeslasher.docker.DefaultDockerClient;
import se.codeslasher.docker.DockerClient;
import se.codeslasher.docker.exception.DockerServerException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class ExecStart {

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
    public void startDetached() {
        final String path = "/v1.24%2Fexec%2F2cf82cdcaca6da3ed97e9099fd180212989da593c254377decd6eecbb00c8179%2Fstart";

        client.startExec("2cf82cdcaca6da3ed97e9099fd180212989da593c254377decd6eecbb00c8179", true);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void startAttached() {
        final String path = "/v1.24%2Fexec%2F8f7abc89befc402cab81d1d01250559e5445f2f94290386e779805c6a274307a%2Fstart";

        InputStream inputStream = client.startExec("8f7abc89befc402cab81d1d01250559e5445f2f94290386e779805c6a274307a");

        assertThat(inputStream).isNotNull();
        List<String> lines = new ArrayList<String>();


        try(BufferedReader reader= new BufferedReader((new InputStreamReader(inputStream)))) {
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(lines.size()).isEqualTo(1);
        assertThat(lines.get(0)).isEqualTo("Thu Sep 29 18:48:28 UTC 2016");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }


}
