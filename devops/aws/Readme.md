# AWS 

Pre-requisites:

* [The Elastic Beanstalk Command Line Interface (EB CLI)](http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3.html)

* [Deploying Elastic Beanstalk Applications from Docker Containers](http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/create_deploy_docker.html)

Scope:

* config

* deployments

* health-checks

* rolling updates

* auto-scaling (based on latency)

## Commands

### Application

1) Create application

```
eb init
```

### Environment

1) [Create environment](http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb3-create.html)

```
eb create <env_name>
```

##### Configuration

1) Save locally

```
eb config save <env_name:optional> --cfg <file_name>
```

2) Upload to S3

```
eb config put <file_name>
```

3) Load config on env

```
eb config --cfg <cfg_name>
```

##### Deployment

1) Change current image in `Dockerrun.aws.json` file
 
2) Push changes to git

3) Deploy changes to Elastic Beanstalk

```
eb deploy
```