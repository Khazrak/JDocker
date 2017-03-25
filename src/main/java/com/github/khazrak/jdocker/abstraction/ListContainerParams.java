package com.github.khazrak.jdocker.abstraction;

import com.github.khazrak.jdocker.utils.Filters;

import java.util.Map;

public interface ListContainerParams {

    boolean isAll();
    int getLimit();
    boolean isSize();
    Filters getFilters();
    Map<String, String> getQueries();

}
