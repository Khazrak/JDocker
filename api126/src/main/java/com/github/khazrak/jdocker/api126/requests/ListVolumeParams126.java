package com.github.khazrak.jdocker.api126.requests;

import com.github.khazrak.jdocker.utils.Filters;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
public class ListVolumeParams126 implements com.github.khazrak.jdocker.abstraction.ListVolumeParams {

    @Builder
    public ListVolumeParams126(String name, String driver, boolean dangling) {
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

    public static class ListVolumeParams126Builder {

        private boolean useDangling;

        public ListVolumeParams126Builder dangling(boolean dangling) {
            this.dangling = dangling;
            useDangling = true;
            return this;
        }

        public ListVolumeParams126 build() {
            ListVolumeParams126 params = new ListVolumeParams126(this.name, this.driver, this.dangling);
            params.useDangling = useDangling;
            return params;
        }

    }

}
