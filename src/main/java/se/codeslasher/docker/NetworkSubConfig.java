package se.codeslasher.docker;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

/**
 * Created by karl on 9/7/16.
 */
@Builder
public class NetworkSubConfig {

    @SerializedName(value = "IPAMConfig")
    private IPAMConfig ipamConfig;

    @Singular
    @SerializedName(value = "Links")
    private List<String> links;

    @Singular
    @SerializedName(value = "Aliases")
    private List<String> aliases;

}
