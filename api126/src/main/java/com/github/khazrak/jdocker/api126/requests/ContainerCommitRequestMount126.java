package com.github.khazrak.jdocker.api126.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.khazrak.jdocker.abstraction.ContainerCommitRequestMount;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContainerCommitRequestMount126 implements ContainerCommitRequestMount {

    @JsonProperty("Source")
    private String source;

    @JsonProperty("Destination")
    private String destination;

    @JsonProperty("Mode")
    private String mode;

    @JsonProperty("RW")
    private boolean readWrite;

}
