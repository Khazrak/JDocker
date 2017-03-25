package com.github.khazrak.jdocker.abstraction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public interface CpuStats {

    CpuUsage getCpuUsage();
    long getSystemCpuUsage();
    Map<String, Long> getThrottlingData();

}
