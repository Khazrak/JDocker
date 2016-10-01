package se.codeslasher.docker.model.api124.parameters;

import lombok.Builder;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.utils.Filters;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by karl on 9/11/16.
 */
@Builder
public class ListContainerParams {

    private static final Logger logger = LoggerFactory.getLogger(ListContainerParams.class);

    @Getter
    private boolean all;

    @Getter
    private int limit;

    @Getter
    private String since;

    @Getter
    private String before;

    @Getter
    private boolean size;

    @Getter
    private Filters filters;

    public Map<String, String> getQueries() {
        Map <String, String> queries = new TreeMap<>();

        if(all) {
            queries.put("all","true");
        }
        if(limit > 0) {
            queries.put("limit", Integer.toString(limit));
        }
        if(since != null) {
            queries.put("since", since);
        }
        if(before != null) {
            queries.put("before", before);
        }
        if(size) {
            queries.put("size","true");
        }
        if(filters != null) {
            queries.put("filters",filters.toString());
        }

        return queries;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        if(all) {
            sb.append("all=true");
        }
        if(limit > 0) {
            append("limit="+limit,sb);
        }
        if(since != null) {
            append("since="+since,sb);
        }
        if(before != null) {
            append("before="+before,sb);
        }
        if(size) {
            append("size=true",sb);
        }
        if(filters != null) {
            sb.append("filters="+filters.toString());
        }

        return sb.toString();
    }



    private void append(String ap, StringBuilder sb) {
        if(sb.length() > 0) {
            sb.append('&');
        }
        sb.append(ap);
    }


}
