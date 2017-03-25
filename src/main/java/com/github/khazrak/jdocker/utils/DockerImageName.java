/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.khazrak.jdocker.utils;

import com.github.khazrak.jdocker.exception.DockerClientException;

import java.util.Optional;

public class DockerImageName {

    public static final String regexp = "[a-zA-Z0-9][a-zA-Z0-9_.-]";

    public static DockerImageName of(String name) {
        return new DockerImageName(name);
    }

    public DockerImageName(String image) {
        String[] parts = image.split("/");
        if (parts.length == 3) {
            registry = Optional.of(new DockerRegistryName(parts[0]));
            repository = Optional.of(new DockerRepositoryName(parts[1]));
            imageName = new DockerImagePartName(parts[2]);
        } else if (parts.length == 2) {
            if (isRegistry(parts[0])) {
                registry = Optional.of(new DockerRegistryName(parts[0]));
            } else {
                repository = Optional.of(new DockerRepositoryName(parts[0]));
            }
            imageName = new DockerImagePartName(parts[1]);
        } else if (parts.length == 1) {
            imageName = new DockerImagePartName(parts[0]);
        } else {
            throw new DockerClientException("Docker ImageInfo Name creation failed, " + image + " is not a valid image-name");
        }


    }


    private Optional<DockerRegistryName> registry = Optional.empty();
    private Optional<DockerRepositoryName> repository = Optional.empty();
    private DockerImagePartName imageName;

    public String getImageRegistry() {
        return this.registry.isPresent() ? this.registry.get().registryName : null;
    }

    public String getImageRepo() {
        return this.repository.isPresent() ? this.repository.get().repoName : null;
    }

    public String getImageName() {
        return this.imageName.name;
    }

    public String getTag() {
        return this.imageName.version;
    }


    public static class DockerRepositoryName {
        private String repoName;

        public DockerRepositoryName(String part) {
            if (part.contains(":") || part.contains("/")) {
                throw new DockerClientException("Repository-name: " + part + " is not valid!");
            }
            repoName = part;
        }
    }

    public static class DockerRegistryName {
        private String registryName;

        public DockerRegistryName(String part) {
            if (part.contains("/")) {
                throw new DockerClientException("Repository-name: " + part + " is not valid!");
            }
            registryName = part;
        }
    }

    public static class DockerImagePartName {
        private String version = "latest";
        private String name;

        public DockerImagePartName(String name) {
            String[] parts = name.split(":");
            if (parts.length == 2) {
                this.name = parts[0];
                this.version = parts[1];
            } else if (parts.length == 1) {
                this.name = name;
            }
        }

        public String toString() {
            return name + ":" + version;
        }

    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        this.registry.ifPresent(drn -> {
            sb.append(drn.registryName);
            sb.append("/");
        });
        this.repository.ifPresent(drn -> {
            sb.append(drn.repoName);
            sb.append("/");
        });
        sb.append(imageName.toString());

        return sb.toString();
    }

    public String toStringWithoutTag() {
        StringBuilder sb = new StringBuilder();
        this.registry.ifPresent(drn -> {
            sb.append(drn.registryName);
            sb.append("/");
        });
        this.repository.ifPresent(drn -> {
            sb.append(drn.repoName);
            sb.append("/");
        });
        sb.append(imageName.name);

        return sb.toString();
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other instanceof DockerImageName == false) {
            return false;
        }

        DockerImageName obj = (DockerImageName) other;

        return this.toString().equals(obj.toString());
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    private boolean isRegistry(String part) {
        return part.contains(":") || part.contains(".");
    }
}
