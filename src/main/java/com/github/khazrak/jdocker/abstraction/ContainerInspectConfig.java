package com.github.khazrak.jdocker.abstraction;

import java.util.List;
import java.util.Map;

public interface ContainerInspectConfig {

    boolean isAttachStderr();
    boolean isAttachStdin();
    boolean isAttachStdout();
    List<String> getCmd();
    String getDomainName();
    List<String> getEntrypoint();
    List<String> getEnvs();
    Map<String, Object> getExposedPorts();
    String getHostName();
    String getImage();
    Map<String,String> getLabels();
    String getMacAddress();
    boolean isNetworkDisabled();
    List<String> getOnBuild();
    boolean isOpenStdin();
    boolean isStdinOnce();
    boolean isTty();
    String getUser();
    Map<String,Map<String,String>> getVolumes();
    String getWorkingDir();
    String getStopSignal();
    boolean isArgsEscaped();

}
