package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface DockerLogsParameters {

    boolean isDetails();
    boolean isFollow();
    boolean isStdout();
    boolean isStderr();
    long getSince();
    boolean isTimestamps();
    Map<String, String> getQueryMap();


}
