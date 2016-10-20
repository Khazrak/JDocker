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

@Getter
@Builder
@JsonDeserialize(builder = ProcessConfig.ProcessConfigBuilder.class)
public class ProcessConfig {

    private List<String> arguments;
    private String entryPoint;
    private boolean privileged;
    private boolean tty;
    private String user;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ProcessConfigBuilder {

        @JsonProperty("arguments")
        private List<String> arguments;

        @JsonProperty("entrypoint")
        private String entryPoint;

        @JsonProperty("privileged")
        private boolean privileged;

        @JsonProperty("tty")
        private boolean tty;

        @JsonProperty("user")
        private String user;

    }

}
