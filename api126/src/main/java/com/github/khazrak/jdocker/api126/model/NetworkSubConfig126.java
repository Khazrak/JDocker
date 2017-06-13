package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.khazrak.jdocker.abstraction.IPAMConfig;
import com.github.khazrak.jdocker.abstraction.NetworkSubConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Getter
@Builder
public class NetworkSubConfig126 implements NetworkSubConfig {

    @JsonDeserialize(as = IPAMConfig126.class)
    @JsonProperty("IPAMConfig")
    private IPAMConfig ipamConfig;

    @Singular
    @JsonProperty("Links")
    private List<String> links;

    @Singular
    @JsonProperty("Aliases")
    private List<String> aliases;

}
