package com.github.khazrak.jdocker.abstraction;

import com.github.khazrak.jdocker.utils.DockerImageName;

import java.util.List;
import java.util.Map;

public interface ContainerCreationRequest {
    String getName();

    String getHostname();

    String getDomainname();

    String getUser();

    boolean isAttachStdin();

    boolean isAttachStdout();

    boolean isAttachStderr();

    HealthCheck getHealthCheck();

    boolean isTty();

    boolean isOpenStdin();

    boolean isStdinOnce();

    List<String> getEnvironmentVariables();

    List<String> getCommands();

    String getEntrypoint();

    DockerImageName getImage();

    Map<String, String> getLabels();

    Map<String, Object> getVolumes();

    String getWorkingDir();

    boolean isNetworkDisabled();

    String getMacAddress();

    Map<String, Object> getExposedPorts();

    String getStopSignal();

    HostConfig getHostConfig();

    NetworkingConfig getNetworkingConfig();

}
