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
./build.sh
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

For **demo** purposes **CockroachDB** has been used as default SQL DB.

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
    
    * Created DB `bouncer` and access to granted to your user

1) Check URL of CockroachDB

```
minikube service cockroachdb-public --url
```

and apply it to `application.yaml` config file.

2) Run locally

```
./run.sh
```

