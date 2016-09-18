package se.codeslasher.docker;

import lombok.Builder;
import lombok.Getter;

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
