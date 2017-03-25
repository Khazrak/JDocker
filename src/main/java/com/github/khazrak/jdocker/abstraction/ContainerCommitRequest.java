package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface ContainerCommitRequest {

    String getContainerName();
    String getRepo();
    String getTag();
    String getComment();
    String getAuthor();
    boolean isPause();
    String getChanges();
    ContainerCommit getContainerCommit();
    Map<String, String> getQueries();

}
