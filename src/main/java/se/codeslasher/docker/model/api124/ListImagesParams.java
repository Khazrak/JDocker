package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by karl on 9/22/16.
 */
@Getter
@Builder
public class ListImagesParams {

    private boolean all;

    private boolean dangling;

    private Map<String,String> labels;

    private String before;

    private String since;

    private String filterByName;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ListImagesParamsBuilder {
        private Map<String,String> labels = new TreeMap<String,String>();
    }

}
