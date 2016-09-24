package se.codeslasher.docker;

import lombok.Builder;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.codeslasher.docker.utils.Filters;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Created by karl on 9/11/16.
 */
@Builder
public class ContainerListRequest {

    private static final Logger logger = LoggerFactory.getLogger(ContainerListRequest.class);

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
            try {
                sb.append("filters="+URLEncoder.encode(filters.toString(), StandardCharsets.UTF_8.toString()));
            } catch (UnsupportedEncodingException e) {
                logger.error("Error encoding filters in container list method",e);
            }
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
