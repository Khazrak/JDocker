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
package com.github.khazrak.jdocker.model.api124.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Builder
public class ContainerCommitRequest {

    private String containerName;
    private String repo;
    private String tag;
    private String comment;
    private String author;
    private boolean pause;
    private String changes;

    private ContainerCommit containerCommit;

    public Map<String, String> getQueries() {
        Map<String, String> queries = new TreeMap<>();

        queries.put("container", containerName);
        if(repo != null) {
            queries.put("repo", repo);
        }
        if(tag != null) {
            queries.put("tag", tag);
        }
        if(comment != null) {
            queries.put("comment", comment);
        }
        if(author != null) {
            queries.put("author", author);
        }
        if(pause) {
            queries.put("pause", Boolean.toString(true));
        }
        if(changes != null) {
            queries.put("changes", changes);
        }

        return queries;
    }


    @Getter
    @Builder
    public static class ContainerCommit {
        @JsonProperty("Hostname")
        private String hostName;

        @JsonProperty("Domainname")
        private String domainName;

        @JsonProperty("User")
        private String user;

        @JsonProperty("AttachStdin")
        private boolean attachStdin;

        @JsonProperty("AttachStdout")
        private boolean attachStdout;

        @JsonProperty("Tty")
        private boolean tty;

        @JsonProperty("OpenStdin")
        private boolean openStdin;

        @JsonProperty("StdinOnce")
        private boolean stdinOnce;

        @JsonProperty("Env")
        private Map<String, String> environment;

        @JsonProperty("Cmd")
        private List<String> command;

        @JsonProperty("Mounts")
        private List<ContainerCommitRequestMount> mounts;

        @JsonProperty("Labels")
        private Map<String, String> labels;

        @JsonProperty("WorkingDir")
        private String workingDir;

        @JsonProperty("NetworkDisabled")
        private boolean networkDisabled;

        @JsonProperty("ExposedPorts")
        private Map<String, Object> exposedPorts;
    }

    @Getter
    @Builder
    public static class ContainerCommitRequestMount {

        @JsonProperty("Source")
        private String source;

        @JsonProperty("Destination")
        private String destination;

        @JsonProperty("Mode")
        private String mode;

        @JsonProperty("RW")
        private boolean readWrite;

    }
}
