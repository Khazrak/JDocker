package com.github.khazrak.jdocker.abstraction;

public interface Mount {

    String getName();
    String getSource();
    String getDestination();
    String getDriver();
    String getMode();
    String getReadwrite();
    String getPropagation();
    String getType();

}
