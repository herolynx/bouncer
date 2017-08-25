# bouncer

Micro-service for user management & auth.

Use cases:

    * use registration
    
    * e-mail sending for verification purposes
    
    * profile edition
    
    * auth with JWT support

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
./docker_build.sh
```

and then

```
./docker_publish.sh <version>
```

## DB

For **demo** purposes **Hazelcast** has been used since it allows to create cluster thus it will be deployed in **Kubernetes**.

Remember that it's **not persisent** storage.

##### Hazelcast

For local development purposes **Hazelcast** has been used.

In order to run it on locally you have to:

1) Create hazelcast on **minikube**

```
kubectl create -f ext/hazelcast
```

2) Check IP and port of your service

```
minikube service hazelcast --url
```

3) Put given IP and port to your `application.yaml` property file.

##### CockroachDB


1) Create CockroachDB service

```
kubectl create -f ext/cockroachdb/statefulset.yaml
```

or 

```
kubectl create -f ext/cockroachdb/statefulset-secure.yaml
```

2) Login locally from console

```
kubectl run -it --rm cockroach-client --image=cockroachdb/cockroach --restart=Never --command -- ./cockroach sql --url="postgresql://cockroachdb:26257/bouncer?sslmode=disable"
```

3) Administration panel

```
./db_proxy.sh
```

and then open `http://localhost:8081` in your browser

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
    
    * Running SQL DB (see DB section)

1) Run DB proxy

```
./db_proxy.sh
```

2) Run locally

```
./run.sh
```

