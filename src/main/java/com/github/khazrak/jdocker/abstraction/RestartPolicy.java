package com.github.khazrak.jdocker.abstraction;

public interface RestartPolicy {

    String getName();

    int getMaximumRetryCount();

}
