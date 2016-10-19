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
import se.codeslasher.docker.model.api124.FileSystemInfo;
import se.codeslasher.docker.utils.RequestStreamBody;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class ContainerFilesystem {

    private DockerClient client;
    private Logger logger = LoggerFactory.getLogger(ContainerFilesystem.class);

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
    public void info() {
        final String path = "/v1.24%2Fcontainers%2Fmongo%2Farchive?path=%2Froot";

        FileSystemInfo info = client.fileSystemInfo("mongo","/root");

        assertThat(info.getName()).isEqualTo("root");
        assertThat(info.getSize()).isEqualTo(58);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.HEAD,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void get() {
        final String path = "/v1.24%2Fcontainers%2Fmongo%2Farchive?path=%2Froot";

        InputStream input = client.fileSystemArchiveDownload("mongo","/root");

        Path p = null;
        File f = null;
        try {
            f = File.createTempFile("myfile",".tar");
            p = f.toPath();
        } catch (IOException e) {
            logger.error("Exception during creating temp file");
        }

        try(BufferedInputStream in = new BufferedInputStream(input); BufferedOutputStream output = new BufferedOutputStream(Files.newOutputStream(p, StandardOpenOption.CREATE))) {
            int data = -1;
            while((data = in.read()) != -1) {
                output.write(data);
            }

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

        assertThat(size).isEqualTo(5120);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.GET,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

    @Test
    public void put() {
        final String path = "/v1.24%2Fcontainers%2Fmongo%2Farchive?path=%2Froot";

        InputStream input = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Path filePath = Paths.get(classLoader.getResource("upload.tar").toURI());
            input = Files.newInputStream(filePath, StandardOpenOption.READ);
        } catch (URISyntaxException e) {
            logger.error("Exception due to URI",e);
        } catch (IOException e) {
            logger.error("Exception due to IO", e);
        }

        RequestStreamBody body = new RequestStreamBody(input);

        client.fileSystemArchiveUpload("mongo", "/root", body);

        UrlPattern pattern = UrlPattern.fromOneOf(path, null,null,null);
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern(RequestMethod.PUT,pattern);

        wireMockRule.verify(1, requestPatternBuilder);
    }

}
