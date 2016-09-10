package se.codeslasher.docker.exception;

/**
 * Created by karl on 9/10/16.
 */
public class DockerServerException extends RuntimeException {

    public DockerServerException(String msg) {
        super(msg);
    }

    public DockerServerException(String msg, Throwable cause) {
        super(msg,cause);
    }

}
