package com.github.khazrak.jdocker.abstraction;

public interface State {

    String getError();

    int getExitCode();

    String getFinishedAt();

    boolean isOomKilled();

    boolean isDead();

    boolean isPaused();

    int getPid();

    boolean isRestarting();

    boolean isRunning();

    String getStartedAt();

    String getStatus();

}
