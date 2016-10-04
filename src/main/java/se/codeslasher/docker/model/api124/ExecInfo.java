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

@Getter
@Builder
@JsonDeserialize(builder = ExecInfo.ExecInfoBuilder.class)
public class ExecInfo {

    private boolean canRemove;
    private String containerId;
    private String detachKeys;
    private int exitCode;
    private String id;
    private boolean openStdErr;
    private boolean openStdIn;
    private boolean openStdOut;
    private ProcessConfig processConfig;
    private boolean running;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ExecInfoBuilder {

        @JsonProperty("CanRemove")
        private boolean canRemove;

        @JsonProperty("ContainerID")
        private String containerId;

        @JsonProperty("DetachKeys")
        private String detachKeys;

        @JsonProperty("ExitCode")
        private int exitCode;

        @JsonProperty("ID")
        private String id;

        @JsonProperty("OpenStderr")
        private boolean openStdErr;

        @JsonProperty("OpenStdin")
        private boolean openStdIn;

        @JsonProperty("OpenStdout")
        private boolean openStdOut;

        @JsonProperty("ProcessConfig")
        private ProcessConfig processConfig;

        @JsonProperty("Running")
        private boolean running;

    }

}
