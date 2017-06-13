package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface NetworkCreateRequest {

    String getName();

    boolean isCheckDuplicate();

    String getDriver();

    boolean isEnableIpv6();

    NetworkCreateIpam getIpam();

    boolean isInternal();

    /**
     * @since Docker api 1.29
     * @return attachable
     */
    boolean isAttachable();

    /**
     * @since Docker api 1.29
     * @return ingress
     */
    boolean isIngress();

    Map<String, String> getOptions();

    Map<String, String> getLabels();

}
