package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface NetworkCreateRequest {

    String getName();

    boolean isCheckDuplicate();

    String getDriver();

    boolean isEnableIpv6();

    NetworkCreateIpam getIpam();

    boolean isInternal();

    Map<String, String> getOptions();

    Map<String, String> getLabels();

}
