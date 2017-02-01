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
@JsonDeserialize(builder = HostPort.HostPortBuilder.class)
public class HostPort {

    @JsonProperty("HostIp")
    private String hostIp;

    @JsonProperty("HostPort")
    private String hostPort;

    @Override
    public String toString() {
        return String.format("Hostport = [%s:%s]", hostIp, hostPort);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class HostPortBuilder {

        @JsonProperty("HostIp")
        private String hostIp;

        @JsonProperty("HostPort")
        private String hostPort;

    }


}
