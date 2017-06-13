package com.github.khazrak.jdocker.api129.model;

import com.github.khazrak.jdocker.abstraction.ListImagesParams;
import com.github.khazrak.jdocker.utils.Filters;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
@Builder
public class ListImagesParams129 implements ListImagesParams {

    private boolean all;

    private boolean dangling;

    private Map<String, String> labels;

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

        if (!this.getLabels().keySet().isEmpty()) {
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

    public static class ListImagesParams129Builder {
        private Map<String, String> labels = new TreeMap<>();
    }

}
