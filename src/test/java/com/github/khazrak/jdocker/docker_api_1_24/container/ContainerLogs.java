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
import com.github.khazrak.jdocker.model.api124.parameters.DockerLogsParameters;
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
import com.github.khazrak.jdocker.handlers.DockerLogsLineReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class ContainerLogs {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(ContainerLogs.class);

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
    public void logs() {
        final String path = "/v1.24%2Fcontainers%2Fmongo%2Flogs?stdout=true";
        int linesCount = 0;
        int expectedLineCount = 31;

        DockerLogsParameters params = DockerLogsParameters.builder().stdout(true).build();

        List<String> logLines = client.logs("mongo", params);

        for(String s : logLines) {
            logger.info(s);
        }

        linesCount = logLines.size();

        assertThat(linesCount).isEqualTo(expectedLineCount);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void logsRawStream() {
        final String path = "/v1.24%2Fcontainers%2Fmongo%2Flogs?stdout=true";
        int linesCount = 0;
        int expectedLineCount = 31;

        DockerLogsParameters params = DockerLogsParameters.builder().stdout(true).build();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(client.logsRawStream("mongo", params)))) {

            String line = "";
            while((line = reader.readLine()) != null) {
                logger.info(line);
                linesCount++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(linesCount).isEqualTo(expectedLineCount);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);

    }

    @Test
    public void logsStream() {
        final String path = "/v1.24%2Fcontainers%2Fmongo%2Flogs?stdout=true";
        int linesCount = 0;
        int expectedLineCount = 31;

        DockerLogsParameters params = DockerLogsParameters.builder().stdout(true).build();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.logsStream("mongo", params)))) {
            String line = "";
            while((line = reader.readLine()) != null) {
                logger.info(line);
                linesCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(linesCount).isEqualTo(expectedLineCount);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void logsSpecial() {
        final String path = "/v1.24%2Fcontainers%2Fmongo%2Flogs?stdout=true";
        int linesCount = 0;
        int expectedLineCount = 31;

        DockerLogsParameters params = DockerLogsParameters.builder().stdout(true).build();

        try(DockerLogsLineReader reader = client.logsSpecial("mongo", params)) {
            String line = "";
            while((line = reader.readLine()) != null) {
                logger.info(line);
                linesCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(linesCount).isEqualTo(expectedLineCount);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    //TODO: Add more test for logging with only stderr in call and the other params

}
