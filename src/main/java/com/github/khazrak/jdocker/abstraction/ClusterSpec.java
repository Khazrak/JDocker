package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface ClusterSpec {

    Map<String, Boolean> getEncryptionConfig();
    Map<String, String> getOrchestration();
    Map<String, String> getRaft();
    Map<String, String> getDispatcher();
    Map<String, String> getCaConfig();
    Map<String, String> getTaskDefaults();

}
