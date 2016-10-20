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
package com.github.khazrak.jdocker.model.api124.parameters;

import com.github.khazrak.jdocker.utils.Filters;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
public class ListVolumeParams {

    @Builder
    public ListVolumeParams(String name, String driver, boolean dangling) {
        this.name = name;
        this.driver = driver;
        this.dangling = dangling;
    }

    private boolean useDangling;

    private String name;

    private String driver;

    private boolean dangling;

    private Filters filters;

    public Map<String, String> getQueries() {
        Map<String, String> queries = new TreeMap<>();

        queries.put("filters", this.toString());

        return queries;
    }

    public String toString() {
        filters = new Filters();

        if(name != null) {
            filters.add("name", name);
        }
        if(driver != null) {
            filters.add("driver", driver);
        }
        if(useDangling) {
            filters.add("dangling", Boolean.toString(dangling));
        }

        return filters.toString();
    }

    public static class ListVolumeParamsBuilder {

        private boolean useDangling;

        public ListVolumeParamsBuilder dangling(boolean dangling) {
            this.dangling = dangling;
            useDangling = true;
            return this;
        }

        public ListVolumeParams build() {
            ListVolumeParams params = new ListVolumeParams(this.name, this.driver, this.dangling);
            params.useDangling = useDangling;
            return params;
        }

    }
}
