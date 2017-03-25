package com.github.khazrak.jdocker.abstraction;

import java.util.List;

public interface RootFS {

    String getType();
    List<String> getLayers();

}
