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
./build_docker.sh
```

and then

```
./publish_docker.sh <version>
```

## DB

For **demo** purposes **Hazelcast** has been used since it allows to create cluster thus it will be deployed in **Kubernetes**.

Remember that it's **not persisent** storage.

In order to deploy it run:

```
kubectl create -f ext/hazelcast
```

## DevOps

DevOps for following clouds/orchestration solution is prepared:

* Kubernetes (`devops/kubernetes`)

* AWS - Elastic Beanstalk (`devops/aws`)

## Peformance tests

Simple performance tests can be done using `vegeta` tool.

Check `perf` directory for more details.

## Local development

Pre-requisites:

    * [minikube](https://github.com/kubernetes/minikube)
    * Maven
    * Java 8

1) Run locally

```
./run.sh
```

##### Hazelcast

For local development purposes **Hazelcast** has been used.

In order to run it on locally you have to:

1) Create hazelcast on **minikube* 

```
kubectl create -f ext/hazelcast
```

2) Check IP and port of your service

```
minikube service hazelcast --url
```

3) Put given IP and port to your `application.yaml` property file.