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

@Getter
@Builder
public class ExecCreateRequest {

    @JsonProperty("AttachStdin")
    private boolean attachStdIn;

    @JsonProperty("AttachStdout")
    private boolean attachStdOut;

    @JsonProperty("AttachStderr")
    private boolean attachStdErr;

    @JsonProperty("Tty")
    private boolean tty;

    @JsonProperty("DetachKeys")
    private String detachKeys;

    @JsonProperty("Cmd")
    private List<String> cmd;

}
