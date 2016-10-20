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

@Getter
@Builder
@JsonDeserialize(builder = DockerVersion.DockerVersionBuilder.class)
public class DockerVersion {

    private String version;
    private String os;
    private String kernelVersion;
    private String goVersion;
    private String gitCommit;
    private String arch;
    private String apiVersion;
    private String buildTime;
    private boolean experimental;

    @JsonPOJOBuilder(withPrefix = "")
    public static class DockerVersionBuilder {

        @JsonProperty("Version")
        private String version;

        @JsonProperty("Os")
        private String os;

        @JsonProperty("KernelVersion")
        private String kernelVersion;

        @JsonProperty("GoVersion")
        private String goVersion;

        @JsonProperty("GitCommit")
        private String gitCommit;

        @JsonProperty("Arch")
        private String arch;

        @JsonProperty("ApiVersion")
        private String apiVersion;

        @JsonProperty("BuildTime")
        private String buildTime;

        @JsonProperty("Experimental")
        private boolean experimental;

    }

}
