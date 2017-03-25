package com.github.khazrak.jdocker.abstraction;

public interface ContainerPort {

    String getIp();

    int getPrivatePort();

    int getPublicPort();

    String getType();

}
