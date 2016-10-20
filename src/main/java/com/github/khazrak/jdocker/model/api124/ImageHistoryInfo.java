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
@JsonDeserialize(builder = ImageHistoryInfo.ImageHistoryInfoBuilder.class)
public class ImageHistoryInfo {

    private String id;
    private long created;
    private String createdBy;
    private List<String> tags;
    private long size;
    private String comment;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ImageHistoryInfoBuilder {

        @JsonProperty("Id")
        private String id;

        @JsonProperty("Created")
        private long created;

        @JsonProperty("CreatedBy")
        private String createdBy;

        @JsonProperty("Tags")
        private List<String> tags;

        @JsonProperty("Size")
        private long size;

        @JsonProperty("Comment")
        private String comment;

    }
}
