package se.codeslasher.docker.model.api124.parameters;

import lombok.Builder;
import lombok.Getter;
import se.codeslasher.docker.utils.Filters;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by karl on 9/22/16.
 */
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
