package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.ClusterSpec;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = ClusterSpec126.ClusterSpec126Builder.class)
public class ClusterSpec126 implements ClusterSpec {

    private Map<String, Boolean> encryptionConfig;
    private Map<String, String> orchestration;
    private Map<String, String> raft;
    private Map<String, String> dispatcher;
    private Map<String, String> caConfig;
    private Map<String, String> taskDefaults;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ClusterSpec126Builder {

        @JsonProperty("EncryptionConfig")
        private Map<String, Boolean> encryptionConfig;

        @JsonProperty("Orchestration")
        private Map<String, String> orchestration;

        @JsonProperty("Raft")
        private Map<String, String> raft;

        @JsonProperty("Dispatcher")
        private Map<String, String> dispatcher;

        @JsonProperty("CAConfig")
        private Map<String, String> caConfig;

        @JsonProperty("TaskDefaults")
        private Map<String, String> taskDefaults;

    }

}
