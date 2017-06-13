package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface CpuStats {

    CpuUsage getCpuUsage();

    long getSystemCpuUsage();

    Map<String, Long> getThrottlingData();

    /**
     * @since Docker api 1.29
     * @return
     */
    int getOnlineCpus();
}
