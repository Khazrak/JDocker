package se.codeslasher.docker.docker_api_1_24;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import se.codeslasher.docker.DefaultDockerClient;
import se.codeslasher.docker.DockerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

/**
 * Created by karl on 9/11/16.
 */
public class ContainerLogs {

    private DockerClient client;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().usingFilesUnderClasspath("src/test/resources/1_24").port(9779)); // No-args constructor defaults

    @Before
    public void setup() {
        client = new DefaultDockerClient("http://127.0.0.1:4243");
    }

    @After
    public void tearDown() {
        client.close();
    }

    @Test
    public void logs() {
        client.logs("mongo");
    }

    @Test
    public void logsStream() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(client.logsStream("mongo")))) {

            String line = "";
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
