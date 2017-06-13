package com.github.khazrak.jdocker.api129.requests;

import com.github.khazrak.jdocker.utils.Filters;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
public class ListVolumeParams129 implements com.github.khazrak.jdocker.abstraction.ListVolumeParams {

    @Builder
    public ListVolumeParams129(String name, String driver, boolean dangling) {
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

        if (name != null) {
            filters.add("name", name);
        }
        if (driver != null) {
            filters.add("driver", driver);
        }
        if (useDangling) {
            filters.add("dangling", Boolean.toString(dangling));
        }

        return filters.toString();
    }

    public static class ListVolumeParams129Builder {

        private boolean useDangling;

        public ListVolumeParams129Builder dangling(boolean dangling) {
            this.dangling = dangling;
            useDangling = true;
            return this;
        }

        public ListVolumeParams129 build() {
            ListVolumeParams129 params = new ListVolumeParams129(this.name, this.driver, this.dangling);
            params.useDangling = useDangling;
            return params;
        }

    }

}
