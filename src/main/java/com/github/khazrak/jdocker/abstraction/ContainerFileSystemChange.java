package com.github.khazrak.jdocker.abstraction;

public interface ContainerFileSystemChange {

    String getPath();
    int getKind();

}
