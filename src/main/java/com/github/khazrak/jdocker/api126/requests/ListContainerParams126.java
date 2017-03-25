package com.github.khazrak.jdocker.api126.requests;

import com.github.khazrak.jdocker.abstraction.ListContainerParams;
import com.github.khazrak.jdocker.utils.Filters;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

@Builder
public class ListContainerParams126 implements ListContainerParams {

    private static final Logger logger = LoggerFactory.getLogger(ListContainerParams126.class);

    @Getter
    private boolean all;

    @Getter
    private int limit;

    @Getter
    private boolean size;

    @Getter
    private Filters filters;

    public Map<String, String> getQueries() {
        Map<String, String> queries = new TreeMap<>();

        if (all) {
            queries.put("all", "true");
        }
        if (limit > 0) {
            queries.put("limit", Integer.toString(limit));
        }
        if (size) {
            queries.put("size", "true");
        }
        if (filters != null) {
            queries.put("filters", filters.toString());
        }

        return queries;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        if (all) {
            sb.append("all=true");
        }
        if (limit > 0) {
            append("limit=" + limit, sb);
        }
        if (size) {
            append("size=true", sb);
        }
        if (filters != null) {
            sb.append("filters=" + filters.toString());
        }

        return sb.toString();
    }


    private void append(String ap, StringBuilder sb) {
        if (sb.length() > 0) {
            sb.append('&');
        }
        sb.append(ap);
    }


}
