package com.github.khazrak.jdocker.abstraction;

import com.github.khazrak.jdocker.utils.Filters;

import java.util.Map;


public interface ListImagesParams {

    boolean isAll();
    boolean isDangling();
    Map<String,String> getLabels();
    String getBefore();
    String getSince();
    String getFilterByName();
    Filters getFilters();
    Map<String, String> getQueries();


}
