package com.github.khazrak.jdocker.abstraction;

import java.util.List;

public interface Plugins {

    List<String> getVolume();
    List<String> getNetwork();
    List<String> getAuthorization();

}
