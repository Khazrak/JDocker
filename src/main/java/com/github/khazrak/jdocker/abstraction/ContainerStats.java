package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface ContainerStats {

       String getId();
       String getName();
       String getRead();
       String getPreRead();
       Map<String, Integer> getPidsStats();
       Map<String, NetworkStats> getNetworksStats();
       MemoryStats getMemoryStats();
       BlkIOStats getBlkioStats();
       CpuStats getCpuStats();
       CpuStats getPreCpuStats();
       int getNumProcs();
       Map<String, String> getStorageStats();

}
