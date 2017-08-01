# Kubernetes
 
Pre-requisites:
 
 * [minikube](https://github.com/kubernetes/minikube)
 
Scope:

* config

* deployments

* health & readiness probes

* rolling updates

## Usage

Service is prepared to be deployed in Kubernetes.

1) Deploy all

```
kubectl create -f devops/kubernetes
```

2) Access cluster

```
kubectl proxy
```

Then open dashboard in web-browser: http://localhost:8001/ui

##### Basic operations

1) Get pods

```
kubectl get pods
```

2) Get services

```
kubectl get services
```

3) Check logs of single pod

```
kubectl logs <pod_name> -c <container_name>
```

4) Deleting stuff

```
kubectl delete service|deployment <name>
```

5) Rollout (provided that `revisionHistoryLimit` > 0)

```
kubectl rollout undo deployment/<name>
```

## Cloud support

### Google Cloud Platform

1) Create a cluster (this step can take a few minutes to complete).

```
gcloud container clusters create ks8-demo-cluster
```

2) Ensure kubectl has authentication credentials:

```
gcloud auth application-default login
```

3) Get config for kubectl

```
gcloud container clusters get-credentials ks8-demo-cluster
```

4) Resize number of nodes in cluster

```
gcloud container clusters resize ks8-demo-cluster --size SIZE
```

### Azure

```
az acs create 
    -g <GROUP_ID> 
    -n ks8-demo-cluster 
    --orchestrator-type kubernetes 
    --generate-ssh-keys
```

### AWS

Check instructions at [KOPS](https://github.com/kubernetes/kops)

