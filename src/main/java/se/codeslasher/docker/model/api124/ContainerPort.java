package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Created by karl on 9/11/16.
 */
public class ContainerPort {


    @Getter
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("IP")
    private String ip;

    @Getter
    @JsonProperty("PrivatePort")
    private int privatePort;

    @Getter
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("PublicPort")
    private int publicPort;

    @Getter
    @JsonProperty("Type")
    private String type;


    public static class ContainerPortBuilder {
        private String type = "tcp";
    }

}
