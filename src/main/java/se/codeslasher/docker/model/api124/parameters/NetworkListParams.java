package se.codeslasher.docker.model.api124.parameters;

import lombok.Builder;
import se.codeslasher.docker.utils.Filters;

import java.util.Map;
import java.util.TreeMap;

@Builder
public class NetworkListParams {

    private String driver;

    private String id;

    private String label;

    private String name;

    private String type;

    public String toString() {
        Filters filter = new Filters();

        if(driver != null) {
            filter.add("driver",driver);
        }

        if(id != null) {
            filter.add("id",id);
        }

        if(label != null) {
            filter.add("label",label);
        }

        if(name != null) {
            filter.add("name",name);
        }

        if(type != null) {
            filter.add("type",type);
        }

        return filter.toString();
    }

    public Map<String, String> getQueries() {
        Map<String, String> queries = new TreeMap<>();
        queries.put("filters", this.toString());
        return queries;
    }
}
