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

## DevOps

DevOps for following clouds/orchestration solution is prepared:

* Kubernetes (`devops/kubernetes`)

* AWS - Elastic Beanstalk (`devops/aws`)

## Peformance tests

Single performance tests can be done using `vegeta` tool.

Check `perf` directory for more details.