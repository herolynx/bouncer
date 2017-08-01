# Performance tests 

Pre-requisites:

* [Vegeta](https://github.com/tsenart/vegeta)

## Usage

1) Run tests

```
vegeta attack \
    -targets=get_hello.txt \
    -duration=300s \
    -rate=1200 \
    -timeout=60s \
    -insecure \
    > output/report_get_hello.bin
```

2) Produce report

```
vegeta report \
    -inputs=output/report_get_hello.bin \
    -reporter=plot \
    > output/plot_get_hello.html
```