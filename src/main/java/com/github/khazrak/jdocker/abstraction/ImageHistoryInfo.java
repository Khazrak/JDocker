package com.github.khazrak.jdocker.abstraction;

import java.util.List;

public interface ImageHistoryInfo {

    String getId();

    long getCreated();

    String getCreatedBy();

    List<String> getTags();

    long getSize();

    String getComment();

}
