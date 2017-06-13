package com.github.khazrak.jdocker.api126.requests;

import com.github.khazrak.jdocker.abstraction.NetworkListParams;
import com.github.khazrak.jdocker.utils.Filters;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
@Builder
public class NetworkListParams126 implements NetworkListParams {

    private String driver;

    private String id;

    private String label;

    private String name;

    private String type;

    public String toString() {
        Filters filter = new Filters();

        if (driver != null) {
            filter.add("driver", driver);
        }

        if (id != null) {
            filter.add("id", id);
        }

        if (label != null) {
            filter.add("label", label);
        }

        if (name != null) {
            filter.add("name", name);
        }

        if (type != null) {
            filter.add("type", type);
        }

        return filter.toString();
    }

    public Map<String, String> getQueries() {
        Map<String, String> queries = new TreeMap<>();
        queries.put("filters", this.toString());
        return queries;
    }

}
