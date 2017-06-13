package com.github.khazrak.jdocker.abstraction;

import java.util.List;
import java.util.Map;

public interface ContainerCommit {

    String getHostName();

    String getDomainName();

    String getUser();

    boolean isAttachStdin();

    boolean isAttachStdout();

    boolean isTty();

    boolean isOpenStdin();

    boolean isStdinOnce();

    Map<String, String> getEnvironment();

    List<String> getCommand();

    List<ContainerCommitRequestMount> getMounts();

    Map<String, String> getLabels();

    String getWorkingDir();

    boolean isNetworkDisabled();

    Map<String, Object> getExposedPorts();

}
