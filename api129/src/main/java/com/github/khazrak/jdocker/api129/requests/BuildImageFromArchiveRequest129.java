package com.github.khazrak.jdocker.api129.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.khazrak.jdocker.abstraction.AuthConfig;
import com.github.khazrak.jdocker.abstraction.BuildImageFromArchiveRequest;
import com.github.khazrak.jdocker.api129.model.AuthConfig129;
import com.github.khazrak.jdocker.utils.DockerImageName;
import com.github.khazrak.jdocker.utils.RequestStreamBody;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;


@Getter
@Builder
public class BuildImageFromArchiveRequest129 implements BuildImageFromArchiveRequest {

    private static final Logger logger = LoggerFactory.getLogger(BuildImageFromArchiveRequest129.class);

    @Singular
    private Map<String, AuthConfig> authConfigs;

    private String dockerFilePath;

    private RequestStreamBody body;

    private DockerImageName tag;

    /**
     * Suppress verbose build output.
     */
    private boolean quiet;

    /**
     * Do not use the cache when building the image.
     */
    private boolean nocache;

    /**
     * Attempt to pull the image even if an older image exists locally
     */
    private boolean pull;

    /**
     * Remove intermediate containers after a successful build (default behavior).
     */
    private boolean removeIntermediateContainers;

    /**
     * Always remove intermediate containers (includes removeIntermediateContainers)
     */
    private boolean forceRm;

    /**
     * Set memory limit for build
     */
    private long memory;

    /**
     * Total memory (memory + swap), -1 to enable unlimited swap
     */
    private long memswap;

    /**
     * CPU shares (relative weight)
     */
    private int cpushares;

    /**
     * CPUs in which to allow execution (e.g., 0-3, 0,1)
     */
    private String cpusetcpus;

    /**
     * The length of a CPU period in microseconds
     */
    private long cpuperiod;

    /**
     * Microseconds of CPU time that the container can get in a CPU period
     */
    private long cpuquota;

    /**
     * Size of /dev/shm in bytes. The size must be greater than 0. If omitted the system uses 64MB
     */
    private long shmsize;

    @Singular
    private Map<String, String> buildargs;

    @Singular
    private Map<String, String> labels;

    private Map<String, String> queries;

    public Map<String, String> getQueries() {
        queries = new TreeMap<>();

        if (dockerFilePath != null) {
            queries.put("dockerfile", dockerFilePath);
        }
        putIfNotNull(dockerFilePath, "dockerfile");
        if (tag != null) {
            queries.put("t", tag.toString());
        }
        putIfTrue(quiet, "q");
        putIfTrue(nocache, "nocache");
        putIfTrue(pull, "pull");
        putIfTrue(removeIntermediateContainers, "rm");
        putIfTrue(forceRm, "forceRm");
        putIfBiggerThenZero(memory, "memory");
        if (memswap > 0 || memswap == -1) {
            queries.put("memswap", Long.toString(memswap));
        }
        putIfBiggerThenZero(cpushares, "cpushares");
        putIfNotNull(cpusetcpus, "cpusetcpus");
        putIfBiggerThenZero(cpuperiod, "cpuperiod");
        putIfBiggerThenZero(cpuquota, "cpuquota");
        putIfBiggerThenZero(shmsize, "shmsize");

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addAbstractTypeMapping(AuthConfig.class, AuthConfig129.class);
        mapper.registerModule(module);


        if (!buildargs.keySet().isEmpty()) {
            try {
                String json = mapper.writeValueAsString(buildargs);
                queries.put("buildargs", json);
            } catch (JsonProcessingException e) {
                logger.error("Exception during json serialization of buildargs");
            }
        }

        if (!labels.keySet().isEmpty()) {
            try {
                String json = mapper.writeValueAsString(labels);
                queries.put("labels", json);
            } catch (JsonProcessingException e) {
                logger.error("Exception during json serialization of labels");
            }
        }

        return queries;
    }

    private void putIfBiggerThenZero(long value, String name) {
        if(value > 0) {
            queries.put(name, Long.toString(value));
        }
    }

    private void putIfBiggerThenZero(int value, String name) {
        if(value > 0) {
            queries.put(name, Integer.toString(value));
        }
    }

    private void putIfTrue(boolean value, String name) {
        if(value) {
            queries.put(name, Boolean.toString(true));
        }
    }

    private void putIfNotNull(String value, String name) {
        if(value != null) {
            queries.put(name, value);
        }
    }

}
