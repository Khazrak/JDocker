package com.github.khazrak.jdocker.abstraction;

import java.util.List;
import java.util.Map;

public interface RegistryConfig {

    List<String> getMirrors();

    Map<String, IndexConfig> getIndexConfigs();

    List<String> getInsecureRegistryCIDRs();

}
