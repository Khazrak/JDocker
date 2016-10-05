# JDocker
Java Docker Client

Docker client in Java that uses tcp or socket. It mapps domain-objects to json and follow Docker Remote API.
Currently only implemented API version 1.24 (Docker 1.12)

The client have support for tcp and unixsocket, Windows namedpipe and test of OSX socket will come.

Most functions have recorded request-response (By Wiremock proxy to docker) and are used in unittest by Wiremock (files and mappings)

## Implemented

### Containers
* List containers
* Create a container
* Inspect a container
* List processes running inside a container
* Get container logs
* Inspect changes on a container�s filesystem
* Get container stats based on resource usage
* Resize a container TTY
* Start a container
* Stop a container
* Restart a container
* Kill a container
* Update a container
* Rename a container
* Pause a container
* Unpause a container
* Remove a container


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


### Misc
* Exec Create
* Exec Inspect
* Exec Start
* Exec Resize
* Ping the docker server
* Show the docker version information
* Display system-wide information


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
* Wait a container
* Retrieving information about files and folders in a container
* Get an archive of a filesystem resource in a container
* Extract an archive of files or folders to a directory in a container


### Images
* Import Image from tarball

### Misc
* Check auth configuration
* Create a new image from a container's changes
* Monitor Docker's events
* Get a tarball containing all images in a repository
* Get a tarball containing all images
* Load a tarball with a set of images and tags into docker
* Image tarball format


SSL/TLS cert for client
Testing of OSX socket
NamedPipe for Windows

More Custom Tests
