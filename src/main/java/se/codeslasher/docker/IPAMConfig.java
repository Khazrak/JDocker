package se.codeslasher.docker;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

/**
 * Created by karl on 9/7/16.
 */
@Builder
public class IPAMConfig {

    @SerializedName(value = "IPv4Address")
    private String ipv4Address;

    @SerializedName(value = "IPv6Address")
    private String ipv6Address;

    @Singular
    @SerializedName(value = "LinkLocalIPs")
    private List<String> linkLocalIps;

}
