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

import lombok.Builder;
import lombok.Getter;
import com.github.khazrak.jdocker.utils.Filters;

import java.util.Map;
import java.util.TreeMap;

@Getter
@Builder
public class ListImagesParams {

    private boolean all;

    private boolean dangling;

    private Map<String,String> labels;

    private String before;

    private String since;

    private String filterByName;

    private Filters filters;

    public Map<String, String> getQueries() {
        Map<String, String> queries = new TreeMap<>();

        queries.put("all", Boolean.toString(all));
        queries.put("filters", this.toString());

        return queries;
    }

    public String toString() {
        filters = new Filters();

        if (this.getLabels().keySet().size() > 0) {
            filters.add(this.getLabels());
        }
        if (this.getBefore() != null) {
            filters.add("before", this.getBefore());
        }
        if (this.getSince() != null) {
            filters.add("since", this.getSince());
        }
        if (this.isDangling()) {
            filters.add("dangling", "true");
        }

        return filters.toString();
    }

    public static class ListImagesParamsBuilder {
        private Map<String,String> labels = new TreeMap<String,String>();
    }

}
