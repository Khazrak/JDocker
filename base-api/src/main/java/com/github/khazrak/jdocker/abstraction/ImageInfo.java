package com.github.khazrak.jdocker.abstraction;

import java.util.List;
import java.util.Map;

public interface ImageInfo {

    int getContainers();

    long getSharedSize();

    String getId();

    String getParentId();

    List<String> getRepoTags();

    List<String> getRepoDigests();

    long getCreated();

    long getSize();

    long getVirtualSize();

    Map<String, String> getLabels();


}
