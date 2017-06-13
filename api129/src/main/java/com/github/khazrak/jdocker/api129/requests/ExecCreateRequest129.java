package com.github.khazrak.jdocker.api129.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.khazrak.jdocker.abstraction.ExecCreateRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ExecCreateRequest129 implements ExecCreateRequest {

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
