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
import com.github.khazrak.jdocker.utils.Filters;

import java.util.Map;
import java.util.TreeMap;

@Builder
public class NetworkListParams {

    private String driver;

    private String id;

    private String label;

    private String name;

    private String type;

    public String toString() {
        Filters filter = new Filters();

        if(driver != null) {
            filter.add("driver",driver);
        }

        if(id != null) {
            filter.add("id",id);
        }

        if(label != null) {
            filter.add("label",label);
        }

        if(name != null) {
            filter.add("name",name);
        }

        if(type != null) {
            filter.add("type",type);
        }

        return filter.toString();
    }

    public Map<String, String> getQueries() {
        Map<String, String> queries = new TreeMap<>();
        queries.put("filters", this.toString());
        return queries;
    }
}
