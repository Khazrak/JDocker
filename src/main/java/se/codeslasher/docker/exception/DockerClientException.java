package se.codeslasher.docker.exception;

/**
 * Created by karl on 9/11/16.
 */
public class DockerClientException extends RuntimeException {
    public DockerClientException(String msg) {
        super(msg);
    }
    public DockerClientException(String msg, Throwable e) {
        super(msg,e);
    }
}
