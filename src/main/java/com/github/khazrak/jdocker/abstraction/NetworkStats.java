package com.github.khazrak.jdocker.abstraction;

public interface NetworkStats {

    int getRxBytes();
    int getRxDropped();
    int getRxErrors();
    int getRxPackets();
    int getTxBytes();
    int getTxDropped();
    int getTxErrors();
    int getTxPackets();

}
