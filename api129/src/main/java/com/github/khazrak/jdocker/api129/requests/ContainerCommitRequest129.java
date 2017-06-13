package com.github.khazrak.jdocker.api129.requests;

import com.github.khazrak.jdocker.abstraction.ContainerCommit;
import com.github.khazrak.jdocker.abstraction.ContainerCommitRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
@Builder
public class ContainerCommitRequest129 implements ContainerCommitRequest {

    private String containerName;
    private String repo;
    private String tag;
    private String comment;
    private String author;
    private boolean pause;
    private String changes;

    private ContainerCommit containerCommit;

    public Map<String, String> getQueries() {
        Map<String, String> queries = new TreeMap<>();

        queries.put("container", containerName);
        if (repo != null) {
            queries.put("repo", repo);
        }
        if (tag != null) {
            queries.put("tag", tag);
        }
        if (comment != null) {
            queries.put("comment", comment);
        }
        if (author != null) {
            queries.put("author", author);
        }
        if (pause) {
            queries.put("pause", Boolean.toString(true));
        }
        if (changes != null) {
            queries.put("changes", changes);
        }

        return queries;
    }

}
