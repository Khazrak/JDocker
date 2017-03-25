package com.github.khazrak.jdocker.abstraction;

public interface FileSystemInfo {

    String getName();
    int getSize();
    long getMode();
    String getMtime();
    String getLinkTarget();

}
