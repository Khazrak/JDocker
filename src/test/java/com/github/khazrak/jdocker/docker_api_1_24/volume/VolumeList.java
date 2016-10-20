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
package com.github.khazrak.jdocker.docker_api_1_24.volume;

import com.github.khazrak.jdocker.model.api124.Volume;
import com.github.khazrak.jdocker.model.api124.parameters.ListVolumeParams;
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

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class VolumeList {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(VolumeList.class);

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
        final String path = "/v1.24%2Fvolumes";

        List<Volume> volumeList = client.listVolumes();

        assertThat(volumeList.size()).isEqualTo(26);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void paramsDanglingUsage() {
        ListVolumeParams params = ListVolumeParams.builder().dangling(false).build();
        assertThat(params.isUseDangling()).isTrue();
        assertThat(params.isDangling()).isFalse();

        ListVolumeParams params2 = ListVolumeParams.builder().dangling(true).build();
        assertThat(params2.isUseDangling()).isTrue();
        assertThat(params2.isDangling()).isTrue();

        ListVolumeParams params3 = ListVolumeParams.builder().build();
        assertThat(params3.isUseDangling()).isFalse();
        assertThat(params3.isDangling()).isFalse();
    }

    @Test
    public void listFiltersDanglingTrue() {

        final String path = "/v1.24%2Fvolumes?filters=%7B%22dangling%22%3A%7B%22true%22%3Atrue%7D%7D";

        ListVolumeParams params = ListVolumeParams.builder().dangling(true).build();

        List<Volume> volumeList = client.listVolumes(params);

        assertThat(volumeList.size()).isEqualTo(20);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);

    }

    @Test
    public void listFiltersDanglingFalse() {

        final String path = "/v1.24%2Fvolumes?filters=%7B%22dangling%22%3A%7B%22false%22%3Atrue%7D%7D";

        ListVolumeParams params = ListVolumeParams.builder().dangling(false).build();

        List<Volume> volumeList = client.listVolumes(params);

        assertThat(volumeList.size()).isEqualTo(6);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);

    }

}
