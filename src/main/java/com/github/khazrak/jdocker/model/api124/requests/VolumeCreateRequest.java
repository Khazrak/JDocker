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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = VolumeCreateRequest.VolumeCreateRequestBuilder.class)
public class VolumeCreateRequest {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("Labels")
    private Map<String, String> labels;

    @JsonProperty("DriverOpts")
    private Map<String, String> driverOpts;

    @JsonPOJOBuilder(withPrefix = "")
    public static class VolumeCreateRequestBuilder {

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Driver")
        private String driver;

        @JsonProperty("Labels")
        private Map<String, String> labels;

        @JsonProperty("DriverOpts")
        private Map<String, String> driverOpts;
    }
}
