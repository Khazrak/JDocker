package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.IndexConfig;
import com.github.khazrak.jdocker.abstraction.RegistryConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = RegistryConfig126.RegistryConfig126Builder.class)
public class RegistryConfig126 implements RegistryConfig {

    private List<String> mirrors;
    private Map<String, IndexConfig> indexConfigs;
    private List<String> insecureRegistryCIDRs;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RegistryConfig126Builder {

        @JsonProperty("Mirrors")
        private List<String> mirrors;

        @JsonProperty("IndexConfigs")
        private Map<String, IndexConfig> indexConfigs;

        @JsonProperty("InsecureRegistryCIDRs")
        private List<String> insecureRegistryCIDRs;


    }

}
