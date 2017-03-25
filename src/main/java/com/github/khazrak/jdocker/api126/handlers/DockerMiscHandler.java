package com.github.khazrak.jdocker.api126.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.khazrak.jdocker.abstraction.DockerVersion;
import com.github.khazrak.jdocker.abstraction.SystemInfo;
import com.github.khazrak.jdocker.api126.DefaultDockerClient126;
import com.github.khazrak.jdocker.api126.model.DockerVersion126;
import com.github.khazrak.jdocker.api126.model.SystemInfo126;
import com.github.khazrak.jdocker.utils.OkHttpExecuter;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DockerMiscHandler {

    private static final Logger logger = LoggerFactory.getLogger(DockerMiscHandler.class);
    private final ObjectMapper mapper;
    private OkHttpExecuter okHttpExecuter;
    private static final String VERSION = DefaultDockerClient126.API_VERSION;

    public DockerMiscHandler(OkHttpExecuter okHttpExecuter, ObjectMapper mapper) {
        this.mapper = mapper;
        this.okHttpExecuter = okHttpExecuter;
    }

    public DockerVersion version() {
        logger.debug("Version");
        final String path = "version";
        Response response = okHttpExecuter.get(path);
        try {
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);
            return mapper.readValue(responseBody, DockerVersion126.class);
        } catch (IOException e) {
            logger.error("Exception during ping", e);
        }
        return null;
    }

    public String ping() {
        logger.debug("Ping");
        final String path = VERSION + "/_ping";
        Response response = okHttpExecuter.get(path);
        try {
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);
            return responseBody;
        } catch (IOException e) {
            logger.error("Exception during ping", e);
        }
        return null;
    }

    public SystemInfo info() {
        logger.debug("Info");
        final String path = "info";
        Response response = okHttpExecuter.get(path);

        try {
            String responseBody = response.body().string();
            logger.debug("Response body: {}", responseBody);
            SystemInfo systemInfo = mapper.readValue(responseBody, SystemInfo126.class);
            return systemInfo;
        } catch (IOException e) {
            logger.error("Exception during info command", e);
        }

        return null;
    }
}
