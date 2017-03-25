package com.github.khazrak.jdocker.abstraction;

import java.util.List;

public interface Warnings {

    List<String> getWarnings();
    String getWarning(int index);
    int size();

}
