package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface MemoryStats {

    Map<String, Long> getStats();

    long getMaxUsage();

    long getUsage();

    int getFailCount();

    long getLimit();

}
