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

}
