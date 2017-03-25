package com.github.khazrak.jdocker.abstraction;


public interface ImageSearchInfo {

    String getDescription();

    boolean isOfficial();

    boolean isAutomated();

    String getName();

    int getStarCount();

}
