package com.github.khazrak.jdocker.abstraction;

public interface ContainerUpdateRequest {

       long getBlkioWeight();
       int getCpuShares();
       long getCpuPeriod();
       long getCpuQuota();
       String getCpuSetCpus();
       String getCpuSetMems();
       long getMemory();
       long getMemorySwap();
       long getMemoryReservation();
       long getKernelMemory();
       RestartPolicy getRestartPolicy();

}
