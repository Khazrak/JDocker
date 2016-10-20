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

import com.github.khazrak.jdocker.DefaultDockerClient;
import com.github.khazrak.jdocker.DockerClient;
import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.khazrak.jdocker.model.api124.Container;
import com.github.khazrak.jdocker.utils.DockerImageName;
import com.github.khazrak.jdocker.model.api124.parameters.ListContainerParams;
import com.github.khazrak.jdocker.utils.Filters;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class ContainerList {

    private DockerClient client;
    private Logger logger = LoggerFactory.getLogger(ContainerList.class);

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
    public void list() {
        final String path = "/v1.24%2Fcontainers%2Fjson";

        List<Container> containerList = client.listContainers();

        assertThat(containerList.size()).isEqualTo(2);

        DockerImageName mongo = new DockerImageName("mongo");

        Container one = containerList.get(0);
        Container two = containerList.get(1);

        logger.info(one.getState());

        assertThat(one.getImage()).isEqualTo(mongo);
        assertThat(two.getImage()).isEqualTo(mongo);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void listAll() {
        final String path = "/v1.24%2Fcontainers%2Fjson?all=true";
        ListContainerParams request = ListContainerParams.builder().all(true).build();
        List<Container> containerList = client.listContainers(request);

        assertThat(containerList.size()).isEqualTo(3);

        DockerImageName mongo = new DockerImageName("mongo");
        DockerImageName ubuntu = new DockerImageName("ubuntu:14.04");

        Container one = containerList.get(0);
        Container two = containerList.get(1);
        Container three = containerList.get(2);

        assertThat(one.getImage()).isEqualTo(mongo);
        assertThat(two.getImage()).isEqualTo(mongo);
        assertThat(three.getImage()).isEqualTo(ubuntu);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void listSince() {
        final String path = "/v1.24%2Fcontainers%2Fjson?since=mongo";
        ListContainerParams request = ListContainerParams.builder().since("mongo").build();
        List<Container> containerList = client.listContainers(request);

        DockerImageName mongo = new DockerImageName("mongo");

        assertThat(containerList.size()).isEqualTo(1);
        assertThat(containerList.get(0).getImage()).isEqualTo(mongo);
        assertThat(containerList.get(0).getNames().get(0)).isEqualTo("/new_mongo");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void listBefore() {
        final String path = "/v1.24%2Fcontainers%2Fjson?before=new_mongo";
        ListContainerParams request = ListContainerParams.builder().before("new_mongo").build();
        List<Container> containerList = client.listContainers(request);

        DockerImageName mongo = new DockerImageName("mongo");

        assertThat(containerList.size()).isEqualTo(2);
        assertThat(containerList.get(0).getImage()).isEqualTo(mongo);
        assertThat(containerList.get(1).getImage()).isEqualTo(mongo);
        assertThat(containerList.get(0).getNames().get(0)).isEqualTo("/new_mongo");
        assertThat(containerList.get(1).getNames().get(0)).isEqualTo("/mongo");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void listLimit() {
        final String path = "/v1.24%2Fcontainers%2Fjson?limit=1";
        ListContainerParams request = ListContainerParams.builder().limit(1).build();
        List<Container> containerList = client.listContainers(request);


        assertThat(containerList.size()).isEqualTo(1);

        DockerImageName mongo = new DockerImageName("mongo");

        Container one = containerList.get(0);

        assertThat(one.getImage()).isEqualTo(mongo);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void listSize() {
        final String path = "/v1.24%2Fcontainers%2Fjson?size=true";
        ListContainerParams request = ListContainerParams.builder().size(true).build();
        List<Container> containerList = client.listContainers(request);

        assertThat(containerList.size()).isEqualTo(2);

        DockerImageName mongo = new DockerImageName("mongo");

        Container one = containerList.get(0);
        Container two = containerList.get(1);

        assertThat(one.getImage()).isEqualTo(mongo);
        assertThat(two.getImage()).isEqualTo(mongo);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void listFilterBefore() {
        final String path = "/v1.24%2Fcontainers%2Fjson?filters=%7B%22before%22%3A%7B%22new_mongo%22%3Atrue%7D%7D";

        Filters filters = new Filters();
        filters.add("before","new_mongo");

        ListContainerParams request = ListContainerParams.builder().filters(filters).build();
        List<Container> containerList = client.listContainers(request);

        assertThat(containerList.size()).isEqualTo(1);
        assertThat(containerList.get(0).getNames().get(0)).isEqualTo("/mongo");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void listFilterSince() {
        final String path = "/v1.24%2Fcontainers%2Fjson?filters=%7B%22since%22%3A%7B%22mongo%22%3Atrue%7D%7D";

        Filters filters = new Filters();
        filters.add("since","mongo");

        ListContainerParams request = ListContainerParams.builder().filters(filters).build();
        List<Container> containerList = client.listContainers(request);

        DockerImageName mongo = new DockerImageName("mongo");

        assertThat(containerList.size()).isEqualTo(1);
        assertThat(containerList.get(0).getNames().get(0)).isEqualTo("/new_mongo");
        assertThat(containerList.get(0).getImage()).isEqualTo(mongo);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }



}
