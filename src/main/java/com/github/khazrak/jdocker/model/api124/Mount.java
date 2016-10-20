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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonPropertyOrder({
        "Name",
        "Source",
        "Destination",
        "Driver",
        "Mode",
        "RW",
        "Propagation"
})
@JsonDeserialize(builder = Mount.MountBuilder.class)
public class Mount {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Source")
    private String source;

    @JsonProperty("Destination")
    private String destination;

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("Mode")
    private String mdoe;

    @JsonProperty("RW")
    private String readwrite;

    @JsonProperty("Propagation")
    private String propagation;

    @JsonPOJOBuilder(withPrefix = "")
    public static class MountBuilder {
        @JsonProperty("Name")
        private String name;

        @JsonProperty("Source")
        private String source;

        @JsonProperty("Destination")
        private String destination;

        @JsonProperty("Driver")
        private String driver;

        @JsonProperty("Mode")
        private String mdoe;

        @JsonProperty("RW")
        private String readwrite;

        @JsonProperty("Propagation")
        private String propagation;
    }

}
