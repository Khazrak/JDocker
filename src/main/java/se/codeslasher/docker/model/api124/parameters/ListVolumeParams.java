package se.codeslasher.docker.model.api124.parameters;

import lombok.Builder;
import lombok.Getter;
import se.codeslasher.docker.utils.Filters;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by karl on 9/24/16.
 */
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
