package com.github.khazrak.jdocker.abstraction;

import java.util.List;

public interface ExecCreateRequest {

    boolean isAttachStdIn();

    boolean isAttachStdOut();

    boolean isAttachStdErr();

    boolean isTty();

    String getDetachKeys();

    List<String> getCmd();

}
