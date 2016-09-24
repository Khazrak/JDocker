package se.codeslasher.docker.model.api124;

import lombok.Builder;
import lombok.Getter;
import se.codeslasher.docker.utils.Filters;

/**
 * Created by karl on 9/24/16.
 */
@Getter
public class VolumeListParams {

    @Builder
    public VolumeListParams(String name, String driver, boolean dangling) {
        this.name = name;
        this.driver = driver;
        this.dangling = dangling;
    }

    private boolean useDangling;

    private String name;

    private String driver;

    private boolean dangling;

    private Filters filters;

    public String toString() {
        filters = new Filters();

        if(name != null) {
            filters.add("name", name);
        }
        if(driver != null) {
            filters.add("driver", driver);
        }
        if(useDangling) {
            filters.add("dangling", Boolean.toString(dangling));
        }

        return filters.toString();
    }

    public static class VolumeListParamsBuilder {

        private boolean useDangling;

        public VolumeListParamsBuilder dangling(boolean dangling) {
            this.dangling = dangling;
            useDangling = true;
            return this;
        }

        public VolumeListParams build() {
            VolumeListParams params = new VolumeListParams(this.name, this.driver, this.dangling);
            params.useDangling = useDangling;
            return params;
        }

    }
}
