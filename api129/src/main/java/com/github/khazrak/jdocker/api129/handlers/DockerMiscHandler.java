package com.github.khazrak.jdocker.api129.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.khazrak.jdocker.abstraction.DockerVersion;
import com.github.khazrak.jdocker.abstraction.SystemInfo;
import com.github.khazrak.jdocker.api129.DefaultDockerClient129;
import com.github.khazrak.jdocker.api129.model.DockerVersion129;
import com.github.khazrak.jdocker.api129.model.SystemInfo129;
import com.github.khazrak.jdocker.utils.OkHttpExecuter;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DockerMiscHandler {

    private static final Logger logger = LoggerFactory.getLogger(DockerMiscHandler.class);
    private final ObjectMapper mapper;
    private OkHttpExecuter okHttpExecuter;
    private static final String VERSION = DefaultDockerClient129.API_VERSION;

    public static final String RESPONSE_BODY_LOG = "Response body: {}";

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
            logger.debug(RESPONSE_BODY_LOG, responseBody);
            return mapper.readValue(responseBody, DockerVersion129.class);
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
            logger.debug(RESPONSE_BODY_LOG, responseBody);
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
            logger.debug(RESPONSE_BODY_LOG, responseBody);
            return mapper.readValue(responseBody, SystemInfo129.class);
        } catch (IOException e) {
            logger.error("Exception during info command", e);
        }

        return null;
    }
}
