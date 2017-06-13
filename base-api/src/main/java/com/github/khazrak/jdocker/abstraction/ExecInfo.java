package com.github.khazrak.jdocker.abstraction;

public interface ExecInfo {

    boolean isCanRemove();

    String getContainerId();

    String getDetachKeys();

    int getExitCode();

    String getId();

    boolean isOpenStdErr();

    boolean isOpenStdIn();

    boolean isOpenStdOut();

    ProcessConfig getProcessConfig();

    boolean isRunning();

    int getPid();
}
