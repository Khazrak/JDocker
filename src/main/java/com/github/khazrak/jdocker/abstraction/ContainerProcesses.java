package com.github.khazrak.jdocker.abstraction;

import java.util.List;

public interface ContainerProcesses {

    List<String> getTitles();

    List<List<String>> getProcesses();

}
