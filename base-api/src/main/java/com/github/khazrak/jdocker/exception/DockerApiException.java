package com.github.khazrak.jdocker.exception;

public class DockerApiException extends RuntimeException {

    public DockerApiException(String msg) {
        super(msg);
    }

    public DockerApiException() {
        super("API too old. Feature not implemented in this Docker API version");
    }

}
