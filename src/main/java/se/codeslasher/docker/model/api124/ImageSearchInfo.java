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
@JsonDeserialize(builder = ImageSearchInfo.ImageSearchInfoBuilder.class)
public class ImageSearchInfo {

    private String description;
    private boolean official;
    private boolean automated;
    private String name;
    private int starCount;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ImageSearchInfoBuilder {

        @JsonProperty("description")
        private String description;

        @JsonProperty("is_official")
        private boolean official;

        @JsonProperty("is_automated")
        private boolean automated;

        @JsonProperty("name")
        private String name;

        @JsonProperty("star_count")
        private int starCount;

    }

}
