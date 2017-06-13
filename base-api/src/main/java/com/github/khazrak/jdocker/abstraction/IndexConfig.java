package com.github.khazrak.jdocker.abstraction;

import java.util.List;

public interface IndexConfig {

    List<String> getMirrors();

    String getName();

    boolean isOfficial();

    boolean isSecure();

}
