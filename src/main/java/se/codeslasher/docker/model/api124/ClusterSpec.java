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
package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = ClusterSpec.ClusterSpecBuilder.class)
public class ClusterSpec {

    private Map<String, String> orchestration;
    private Map<String, String> raft;
    private Map<String, String> dispatcher;
    private Map<String, String> caConfig;
    private Map<String, String> taskDefaults;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ClusterSpecBuilder {

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

