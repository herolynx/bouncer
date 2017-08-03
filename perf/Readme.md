# Performance tests 

Pre-requisites:

* [Vegeta](https://github.com/tsenart/vegeta)

## Usage

1) Run tests

```
vegeta attack \
    -targets=get_users.txt \
    -duration=30s \
    -rate=1200 \
    -timeout=60s \
    -insecure \
    > output/report_get_users.bin
```

2) Produce report

```
vegeta report \
    -inputs=output/report_get_users.bin \
    -reporter=plot \
    > output/plot_get_users.html
```

or in text form:

```
vegeta report \
    -inputs=output/report_get_users.bin \
    -reporter=text 
```
