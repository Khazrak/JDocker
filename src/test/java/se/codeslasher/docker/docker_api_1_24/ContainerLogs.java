package se.codeslasher.docker.docker_api_1_24;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.DefaultDockerClient;
import se.codeslasher.docker.DockerClient;
import se.codeslasher.docker.handlers.DockerLogsLineReader;
import se.codeslasher.docker.model.api124.DockerLogsParameters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by karl on 9/11/16.
 */
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
        int linesCount = 0;
        int expectedLineCount = 31;

        DockerLogsParameters params = DockerLogsParameters.builder().stdout(true).build();

        List<String> logLines = client.logs("mongo", params);

        for(String s : logLines) {
            logger.info(s);
        }

        linesCount = logLines.size();

        assertThat(linesCount).isEqualTo(expectedLineCount);
    }

    @Test
    public void logsRawStream() {

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

    }

    @Test
    public void logsStream() {
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
    }

    @Test
    public void logsSpecial() {
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
    }

    //TODO: Add more test for logging with only stderr in call and the other params

}
