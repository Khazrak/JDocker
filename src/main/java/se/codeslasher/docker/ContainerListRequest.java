package se.codeslasher.docker;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by karl on 9/11/16.
 */
@Builder
public class ContainerListRequest {

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
    private String filters;

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
            append("filters="+filters,sb);
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
