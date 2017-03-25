package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.FileSystemInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = FileSystemInfo126.FileSystemInfo126Builder.class)
public class FileSystemInfo126 implements FileSystemInfo {

    private String name;
    private int size;
    private long mode;
    private String mtime;
    private String linkTarget;

    @JsonPOJOBuilder(withPrefix = "")
    public static class FileSystemInfo126Builder {

        @JsonProperty("name")
        private String name;

        @JsonProperty("size")
        private int size;

        @JsonProperty("mode")
        private long mode;

        @JsonProperty("mtime")
        private String mtime;

        @JsonProperty("linkTarget")
        private String linkTarget;

    }

}
