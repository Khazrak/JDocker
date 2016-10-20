/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.khazrak.jdocker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = Image.ImageBuilder.class)
public class Image {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("ParentId")
    private String parentId;

    @JsonProperty("RepoTags")
    private List<String> repoTags;

    @JsonProperty("RepoDigests")
    private List<String> repoDigests;

    @JsonProperty("Created")
    private String created;

    @JsonProperty("Size")
    private long size;

    @JsonProperty("VirtualSize")
    private long virtualSize;

    @JsonProperty("Labels")
    private Map<String,String> labels;

    @JsonProperty("Parent")
    private String parent;

    @JsonProperty("Comment")
    private String comment;

    @JsonProperty("Os")
    private String os;

    @JsonProperty("Architecture")
    private String architecture;

    @JsonProperty("Container")
    private String container;

    @JsonProperty("ContainerConfig")
    private ImageContainerConfig containerConfig;

    @JsonProperty("DockerVersion")
    private String dockerVersion;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ImageBuilder {

        @JsonProperty("Id")
        private String id;

        @JsonProperty("ParentId")
        private String parentId;

        @JsonProperty("RepoTags")
        private List<String> repoTags;

        @JsonProperty("RepoDigests")
        private List<String> repoDigests;

        @JsonProperty("Created")
        private String created;

        @JsonProperty("Size")
        private long size;

        @JsonProperty("VirtualSize")
        private long virtualSize;

        @JsonProperty("Labels")
        private Map<String,String> labels;

        @JsonProperty("Parent")
        private String parent;

        @JsonProperty("Comment")
        private String comment;

        @JsonProperty("Os")
        private String os;

        @JsonProperty("Architecture")
        private String architecture;

        @JsonProperty("Container")
        private String container;

        @JsonProperty("ContainerConfig")
        private ImageContainerConfig containerConfig;

        @JsonProperty("DockerVersion")
        private String dockerVersion;



        @JsonProperty("Author")
        private String author;

        @JsonProperty("Config")
        private ImageContainerConfig config;

        @JsonProperty("GraphDriver")
        private GraphDriver graphDriver;

        @JsonProperty("RootFS")
        private RootFS rootFs;
    }

}
