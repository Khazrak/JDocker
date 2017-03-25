package com.github.khazrak.jdocker.abstraction;

public interface ContainerCommitRequestMount {

    String getSource();

    String getDestination();

    String getMode();

    boolean isReadWrite();

}
