package com.github.khazrak.jdocker.abstraction;

import java.util.List;

public interface CpuUsage {

    List<Long> getPerCpuUsage();
    Long getUsageInUsermode();
    long getTotalUsage();
    long getUsageInKernelMode();

}
