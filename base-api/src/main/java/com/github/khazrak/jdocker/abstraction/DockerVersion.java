package com.github.khazrak.jdocker.abstraction;

public interface DockerVersion {


    String getVersion();

    String getOs();

    String getKernelVersion();

    String getGoVersion();

    String getGitCommit();

    String getArch();

    String getApiVersion();

    String getBuildTime();

    boolean isExperimental();

}
