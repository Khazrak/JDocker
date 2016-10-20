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
import com.github.khazrak.jdocker.utils.DockerImageName;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageTarballDownload {

    private DockerClient client;
    private static Logger logger = LoggerFactory.getLogger(ImageTarballDownload.class);

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
    public void downloadBusyBox() {

        final String path = "/v1.24%2Fimages%2Fget?names=busybox%3Alatest";

        DockerImageName imageName = new DockerImageName("busybox");

        InputStream input = client.getImageTar(imageName);

        Path p = null;
        File f = null;
        try {
            f = File.createTempFile("busybox-images",".tar");
            p = f.toPath();
        } catch (IOException e) {
            logger.error("Exception during creating temp file");
        }

        try(BufferedInputStream in = new BufferedInputStream(input); BufferedOutputStream writer = new BufferedOutputStream(Files.newOutputStream(p, StandardOpenOption.CREATE))) {
            int data = -1;
            while((data = in.read()) != -1) {
                writer.write(data);
            }
            writer.flush();
        } catch (IOException e) {
            logger.error("Excpetion during download of tar", e);
        }

        long size = -1;
        try {
            size = Files.size(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            f.delete();
        }

        assertThat(size).isEqualTo(1303040L);


        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);

    }



}
