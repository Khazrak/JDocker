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

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageTarballImport {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(ImageTarballImport.class);

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
    public void importBusyBox() {

        final String path = "/v1.24%2Fimages%2Fload?quiet=false";

        InputStream input = null;
        Path filePath = null;
        String output = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            filePath = Paths.get(classLoader.getResource("1_24/__files/busybox-image.tar").toURI());

            input = Files.newInputStream(filePath, StandardOpenOption.READ);
            output = client.importImageTar(input, false);
            logger.info(output);
        } catch (URISyntaxException e) {
            logger.error("Exception due to URI", e);
        } catch (IOException e) {
            logger.error("Exception due to IO", e);
        }


        assertThat(output).contains("{\"stream\":\"Loaded image: busybox:latest\\n\"}");

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.POST,pattern);

        wireMockRule.verify(1, requestPatternBuilder);

    }

}
