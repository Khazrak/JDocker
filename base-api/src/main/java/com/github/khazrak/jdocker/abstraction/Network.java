package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface Network {

    String getName();

    String getId();

    String getDriver();

    String getScope();

    boolean isEnableIpv6();

    NetworkIPAM getIpam();

    boolean isInternal();

    Map<String, String> getOptions();

    /**
     * @since api 1.29
     * @return ingress
     */
    boolean isIngress();

    Map<String, NetworkContainer> getContainers();

    Map<String, String> getLabels();

    String getCreated();

    boolean isAttachable();

}
