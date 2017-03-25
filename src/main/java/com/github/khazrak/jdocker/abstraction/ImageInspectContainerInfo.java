package com.github.khazrak.jdocker.abstraction;

import java.util.List;
import java.util.Map;

public interface ImageInspectContainerInfo {

    boolean isAttachStderr();
    boolean isAttachStdin();
    boolean isAttachStdout();
    List<String> getCmd();
    String getDomainName();
    List<String> getEntrypoint();
    List<String> getEnvs();
    Map<String, Object> getExposedPorts();
    String getHostName();
    boolean isTty();
    boolean isOpenStdin();
    boolean isStdinOnce();
    boolean isArgsEscaped();
    String getImage();
    Map<String,String> getLabels();
    boolean isNetworkDisabled();
    List<String> getOnBuild();
    String getPublishService();
    String getUser();
    Map<String,Map<String,String>> getVolumes();
    String getWorkingDir();


}
