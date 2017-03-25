package com.github.khazrak.jdocker.abstraction;

import java.util.List;

public interface ProcessConfig {

    List<String> getArguments();

    String getEntryPoint();

    boolean isPrivileged();

    boolean isTty();

    String getUser();

}
