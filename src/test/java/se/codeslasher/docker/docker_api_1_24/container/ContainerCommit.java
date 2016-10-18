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
package se.codeslasher.docker.docker_api_1_24.container;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import org.assertj.core.api.ObjectArrayAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import se.codeslasher.docker.DefaultDockerClient;
import se.codeslasher.docker.DockerClient;
import se.codeslasher.docker.model.api124.HostConfig;
import se.codeslasher.docker.model.api124.requests.ContainerCommitRequest;
import se.codeslasher.docker.model.api124.requests.ContainerCreationRequest;

import java.util.Map;
import java.util.TreeMap;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class ContainerCommit {

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
    public void commitContainer()  {

        final String path = "/v1.24%2Fcommit?container=mongo&pause=true&repo=test&tag=my_version";

        Map<String, Object> exposedPorts = new TreeMap<>();
        exposedPorts.put("1337/tcp", new Object());

        ContainerCommitRequest.ContainerCommit commit = ContainerCommitRequest.ContainerCommit.builder()
                .exposedPorts(exposedPorts)
                .build();

        ContainerCommitRequest containerCommitRequest = ContainerCommitRequest.builder()
                .containerCommit(commit)
                //.changes("RUN echo 'kasnke' >> /testar.txt \nRUN echo 'blabla' >> /bla.txt")
                .pause(true)
                .repo("test")
                .containerName("mongo")
                .tag("my_version")
                .build();

        String id = client.commitContainer(containerCommitRequest);

        assertThat(id).isEqualTo("sha256:2a163ed6ef6cb68c2d63ad40585f34b5f34a97b85dd0b13437a2e922b853504d");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void commitContainerDockerFileCommands()  {

        final String path = "/v1.24%2Fcommit?changes=CMD+echo+%27kasnke%27&container=mongo&pause=true&repo=test%2Fmongo&tag=my_version";

        Map<String, Object> exposedPorts = new TreeMap<>();
        exposedPorts.put("1337/tcp", new Object());

        ContainerCommitRequest.ContainerCommit commit = ContainerCommitRequest.ContainerCommit.builder()
                .exposedPorts(exposedPorts)
                .build();

        ContainerCommitRequest containerCommitRequest = ContainerCommitRequest.builder()
                .containerCommit(commit)
                .changes("CMD echo 'kasnke'")
                .pause(true)
                .repo("test/mongo")
                .containerName("mongo")
                .tag("my_version")
                .build();

        String id = client.commitContainer(containerCommitRequest);

        assertThat(id).isEqualTo("sha256:1f14e91bae81d95fb744a2cf70b41d707c1d1ba981fcdc8cb36da07e9c674aed");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

}
