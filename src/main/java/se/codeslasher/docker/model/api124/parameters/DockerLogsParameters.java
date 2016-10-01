package se.codeslasher.docker.model.api124.parameters;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by karl on 9/18/16.
 */
@Builder
@Getter
public class DockerLogsParameters {

    private boolean details;
    private boolean follow;
    private boolean stdout;
    private boolean stderr;

    /**
     * Unix Timestamp, retrieves logs since this timestamp
     */
    private long since;
    private boolean timestamps;

    /**
     * Number of logs to tail from, (default is all)
     */
    private int tail;

    public Map<String, String> getQueryMap() {
        Map <String, String> queries = new TreeMap<>();

        queries.put("stdout", Boolean.toString(stdout));
        if(stderr) {
            queries.put("stderr", Boolean.toString(true));
        }
        if(details) {
            queries.put("details", Boolean.toString(true));
        }
        if(timestamps) {
            queries.put("timestamps", Boolean.toString(true));
        }
        if(since != 0) {
            queries.put("since", Long.toString(since));
        }
        if(tail > 0) {
            queries.put("tail", Integer.toString(tail));
        }

        return queries;
    }

    public String toString() {
        String params = "?stdout="+stdout;
        if(stderr) {
            params += "&stderr=true";
        }
        if(details) {
            params += "&details=true";
        }
        if(timestamps) {
            params += "%timestamps=true";
        }
        if(since != 0) {
            params += "&since=" + since;
        }
        if(tail > 0) {
            params += "&tail="+tail;
        }

        if(params.length() == 1) {
            params = "";
        }
        return params;
    }


}
