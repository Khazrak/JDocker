package com.github.khazrak.jdocker.abstraction;

public interface HealthCheck {
    String [] getTest();
    int getInterval();
    int getTimeout();
    int getRetries();

}
