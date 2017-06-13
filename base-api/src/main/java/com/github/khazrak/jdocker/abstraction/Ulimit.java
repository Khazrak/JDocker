package com.github.khazrak.jdocker.abstraction;

public interface Ulimit {

    String getName();

    int getSoft();

    int getHard();

}
