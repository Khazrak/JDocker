package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.khazrak.jdocker.abstraction.*;
import com.github.khazrak.jdocker.utils.DockerImageName;
import lombok.Getter;
import lombok.Singular;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Getter
public class Container126 implements Container {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("Names")
    private List<String> names;

    @JsonProperty("Image")
    private DockerImageName image;

    @JsonProperty("ImageID")
    private String imageId;

    @JsonProperty("Command")
    private String command;

    @JsonProperty("Created")
    private long created;

    @JsonProperty("State")
    private String state;

    @JsonProperty("Status")
    private String status;

    @Singular
    @JsonProperty("Ports")
    private List<ContainerPort> ports;

    @Singular
    @JsonProperty("Labels")
    private Map<String, String> labels;

    @JsonProperty("SizeRw")
    private long sizeRw;

    @JsonProperty("SizeRootFs")
    private long sizeRootFs;

    @JsonDeserialize(as = HostConfig126.class)
    @JsonProperty("HostConfig")
    private HostConfig hostConfig;

    @JsonDeserialize(as = NetworkSettings126.class)
    @JsonProperty("NetworkSettings")
    private NetworkSettings networkSettings;

    @JsonProperty("Mounts")
    private List<Mount> mounts;

    @Override
    public String toString() {
        String portsAsString = this.ports.toString();
        String namesAsString = this.names.toString();
        return id.substring(0, 12) + "\t" +
                image + "\t" +
                command + "\t" +
                getCreatedTime() + "\t" +
                status + "\t" +
                portsAsString.substring(1, portsAsString.length() - 1) + "\t" +
                namesAsString.substring(1, namesAsString.length() - 1);
    }

    private String getCreatedTime() {
        Instant now = Instant.now();
        Instant then = Instant.ofEpochSecond(created);

        Duration time = Duration.between(then, now);


        String res = "";

        if (time.toDays() > 0) {
            res += time.toDays() + " days";
        }

        if (time.toHours() > 0) {
            res += time.toHours() + " hours";
        } else {
            res = time.toMinutes() + " minutes";
        }

        if (time.getSeconds() < 60) {
            res = time.getSeconds() + " seconds";
        }

        return res + " ago";
    }

}
