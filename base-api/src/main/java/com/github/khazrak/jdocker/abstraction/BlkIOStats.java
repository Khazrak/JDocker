package com.github.khazrak.jdocker.abstraction;

import java.util.List;

public interface BlkIOStats {

    List<IOServiceBytes> getIoServiceBytesRecursive();

    List<IOServiceBytes> getIoQueueRecursive();

    List<IOServiceBytes> getIoServiceTimeRecursive();

    List<IOServiceBytes> getIoWaitTimeRecursive();

    List<IOServiceBytes> getIoMergedRecursive();

    List<IOServiceBytes> getIoTimeRecursive();

    List<IOServiceBytes> getSectorsRecursive();

}
