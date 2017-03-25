package com.github.khazrak.jdocker.abstraction;

import java.util.List;
import java.util.Map;

public interface ImageInspect {

    String getId();
    String getParentId();
    List<String> getRepoTags();
    List<String> getRepoDigests();
    String getCreated();
    long getSize();
    long getVirtualSize();
    Map<String,String> getLabels();
    String getParent();
    String getComment();
    String getOs();
    String getArchitecture();
    String getContainer();
    ImageInspectContainerInfo getContainerConfig();
    String getDockerVersion();

}
