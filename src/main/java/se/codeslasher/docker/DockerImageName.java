package se.codeslasher.docker;

import se.codeslasher.docker.exception.DockerClientException;

import java.util.Optional;

/**
 * Created by karl on 9/11/16.
 */

public class DockerImageName {

    public DockerImageName(String image) {
        String [] parts = image.split("/");
        if(parts.length == 3) {
            registry = Optional.of(new DockerRegistryName(parts[0]));
            repository = Optional.of(new DockerRepositoryName(parts[1]));
            imageName = new DockerImagePartName(parts[2]);
        }
        else if(parts.length == 2) {
            repository = Optional.of(new DockerRepositoryName(parts[0]));
            imageName = new DockerImagePartName(parts[1]);
        }
        else if(parts.length == 1) {
            imageName = new DockerImagePartName(parts[0]);
        }
        else {
            throw new DockerClientException("Docker Image Name creation failed, "+image+" is not a valid image-name");
        }


    }

    private Optional<DockerRegistryName> registry = Optional.empty();
    private Optional<DockerRepositoryName> repository = Optional.empty();
    private DockerImagePartName imageName;


    public static class DockerRepositoryName {
        private String repoName;

        public DockerRepositoryName(String part) {
            if(part.contains(":") || part.contains("/")) {
                throw new DockerClientException("Repository-name: " + part + " is not valid!");
            }
            repoName = part;
        }
    }
    public static class DockerRegistryName {
        private String registryName;

        public DockerRegistryName(String part) {
            if(part.contains("/")) {
                throw new DockerClientException("Repository-name: " + part + " is not valid!");
            }
            registryName = part;
        }
    }

    public static class DockerImagePartName {
        private String version = "latest";
        private String name;

        public DockerImagePartName(String name) {
            String [] parts = name.split(":");
            if(parts.length == 2) {
                this.name = parts[0];
                this.version = parts [1];
            }
            else if(parts.length == 1) {
                this.name = name;
            }
        }
        public String toString() {
            return name + ":" + version;
        }

    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        this.registry.ifPresent(drn -> {sb.append(drn.registryName);sb.append("/");});
        this.repository.ifPresent(drn -> {sb.append(drn.repoName);sb.append("/");});
        sb.append(imageName.toString());

        return sb.toString();
    }


    public boolean equals(Object other) {
        if(other == null) {
            return false;
        }
        if(other instanceof DockerImageName == false) {
            return false;
        }

        DockerImageName obj = (DockerImageName)other;

        return this.toString().equals(obj.toString());
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

}
