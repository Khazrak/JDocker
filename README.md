# JDocker
Java Docker Client

Docker client in Java that uses tcp or socket. It mapps domain-objects to json and follow Docker Remote API.
Currently only implemented API version 1.26 (Docker 1.13.1 & 17.03.0) (Earlier 1.24 but after refactoring the new baseline will be 1.26)
A new structure have been implemented to make backwards compatibility better (new things will throw Exceptions if used on old API versions)

The client have support for tcp, unixsocket and Windows namedpipe (holy ship!), test of OSX socket will come (it might already work).

Most functions have recorded request-response (By Wiremock proxy to docker) and are used in unittest by Wiremock (files and mappings)


## Setting up the client

Gradle:
```groovy
compile 'com.github.khazrak:jdocker-client:2.0.0'
```

Maven:
```xml
<dependency>
    <groupId>com.github.khazrak</groupId>
    <artifactId>jdocker-client</artifactId>
    <version>2.0.0</version>
</dependency>
```


**Remote Unsecure tcp**
```java
DockerClient client = new DefaultDockerClient126("http://127.0.0.1:4243");
```
**Remote secure tcp**
```java
DockerClient client = new DefaultDockerClient126("https://127.0.0.1:2376", "/path/to/ssl/certs");
```
**Unix socket/named Pipe**
```java
DockerClient client = new DefaultDockerClient126();
```

Wrapper classes (EasyContainer)
```java
DockerClient client = new DefaultDockerClient126();

EasyContainer container = new EasyContainer("mongo");
container.net("my-net")
      .name("my-mongo")
      .addAlias("haha-mongo")
      .addPublishPort("0.0.0.0",1337,8080)
      .addVolume(Paths.get("/tmp"))
      .addVolume("my-vol", Paths.get("/my_volume"))
      .addHostVolume(Paths.get("/tmp/logs"), Paths.get("/var/log"))
      //.cmd("echo 'hello'")
      .addEnvVariable("key","value");

ContainerCreationRequest containerCreationRequest = container.buildRequest();

Map<String, List<HostPort>> portBindings = containerCreationRequest.getHostConfig().getPortBindings();

System.out.println(portBindings);

String id = client.createContainer(containerCreationRequest);
client.start(id);

client.close();
```

## Building from source

You need to add these properties to your project:

```
ossrhUsername=null
ossrhPassword=null
```



## Implemented

### Containers
* List containers
* Create a container
* Inspect a container
* List processes running inside a container
* Get container logs
* Inspect changes on a container's filesystem
* Get container stats based on resource usage
* Resize a container TTY
* Start a container
* Stop a container
* Restart a container
* Kill a container
* Wait a container
* Update a container
* Rename a container
* Pause a container
* Unpause a container
* Remove a container
* Retrieving information about files and folders in a container
* Get an archive of a filesystem resource in a container
* Extract an archive of files or folders to a directory in a container


### Images
* List Images
* Create an image
* Inspect an image
* Tag an image into a repository
* Push an image on the registry
* Remove an image
* Search images
* Get the history of an image
* Build image from a Dockerfile
* Pull Image
* Get a tarball containing all images in a repository
* Load a tarball with a set of images and tags into docker

### Misc
* Exec Create
* Exec Inspect
* Exec Start
* Exec Resize
* Ping the docker server
* Show the docker version information
* Display system-wide information
* Check auth configuration
* Create a new image from a container's changes


### Volumes
* List volumes
* Create a volume
* Inspect a volume
* Remove a volume


### Networks
* List networks
* Create a network
* Inspect network
* Remove a network
* Connect a container to a network
* Disconnect a container from a network

## TODO

### Container
* Attach to a container
* Attach to a container (websocket)

### Misc
* Monitor Docker's events


* Testing of OSX socket

More Custom Tests
