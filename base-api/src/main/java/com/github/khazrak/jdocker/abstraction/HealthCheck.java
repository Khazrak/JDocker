package com.github.khazrak.jdocker.abstraction;

public interface HealthCheck {
    String[] getTest();

    long getInterval();

    int getTimeout();

    int getRetries();

}
