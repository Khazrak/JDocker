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

import java.util.Iterator;
import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = Warnings.WarningsBuilder.class)
public class Warnings implements Iterable<String> {

    @JsonProperty("Warnings")
    private List<String> warnings;

    public String getWarning(int index) {
        return warnings.get(index);
    }

    public int size() {
        return warnings.size();
    }

    @Override
    public Iterator<String> iterator() {
        return new WarningsIterator();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class WarningsBuilder {

        @JsonProperty("Warnings")
        private List<String> warnings;

    }

    public class WarningsIterator implements Iterator<String>  {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index <= warnings.size();
        }

        @Override
        public String next() {
            return warnings.get(index++);
        }
    }

}
