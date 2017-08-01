# AWS 

Pre-requisites:

* [The Elastic Beanstalk Command Line Interface (EB CLI)](http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3.html)

* [Deploying Elastic Beanstalk Applications from Docker Containers](http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/create_deploy_docker.html)

## Commands

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