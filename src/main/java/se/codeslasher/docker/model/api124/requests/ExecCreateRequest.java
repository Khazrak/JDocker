package se.codeslasher.docker.model.api124.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by karl on 9/29/16.
 */
@Getter
@Builder
public class ExecCreateRequest {

    @JsonProperty("AttachStdin")
    private boolean attachStdIn;

    @JsonProperty("AttachStdout")
    private boolean attachStdOut;

    @JsonProperty("AttachStderr")
    private boolean attachStdErr;

    @JsonProperty("Tty")
    private boolean tty;

    @JsonProperty("DetachKeys")
    private String detachKeys;

    @JsonProperty("Cmd")
    private List<String> cmd;

}
