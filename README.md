# seed-user-management-backend

Seed of micro-service for user management

## Build

1) Build project

```
mvn clean build
```

or

```
mvn clean istall
```

2) Build and push docker image

```
mvn clean package docker:build -DpushImage -DpushImageTags -DdockerImageTags=<VERSION>
```

## Local development

Pre-requisites:

    * [minikube](https://github.com/kubernetes/minikube)
    * Maven
    * Java 8

1) Run locally

```
./run.sh
```