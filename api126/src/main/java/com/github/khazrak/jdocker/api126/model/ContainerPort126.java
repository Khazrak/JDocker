package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.ContainerPort;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonDeserialize(builder = ContainerPort126.ContainerPort126Builder.class)
public class ContainerPort126 implements ContainerPort {

    @Getter
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("IP")
    private String ip;

    @Getter
    @JsonProperty("PrivatePort")
    private int privatePort;

    @Getter
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("PublicPort")
    private int publicPort;

    @Getter
    @JsonProperty("Type")
    private String type;

    @Override
    public String toString() {
        if (ip == null) {
            return privatePort + "/" + type;
        }
        return ip + ":" + publicPort + "-> " + privatePort + "/" + type;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class ContainerPort126Builder {

        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        @JsonProperty("IP")
        private String ip;

        @JsonProperty("PrivatePort")
        private int privatePort;

        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        @JsonProperty("PublicPort")
        private int publicPort;

        @JsonProperty("Type")
        private String type = "tcp";
        
    }

}
