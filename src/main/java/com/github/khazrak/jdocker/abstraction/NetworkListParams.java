package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface NetworkListParams {

    String getDriver();
    String getId();
    String getLabel();
    String getName();
    String getType();
    Map<String, String> getQueries();

}
