package com.github.khazrak.jdocker;

import com.github.khazrak.jdocker.utils.DockerImageName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EasyClient extends DefaultDockerClient {

    private static Logger logger = LoggerFactory.getLogger(EasyClient.class);

    public void run(String image, String name) {

        DockerImageName imageName = new DockerImageName(image);
        run(imageName, name);
    }

    public void run(DockerImageName image, String name) {

    }

}
