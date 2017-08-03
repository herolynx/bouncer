#!/bin/bash

VERSION="$1"

if [ -z "${VERSION}" ]; then
    echo "VERSION is unset. Run ./publish_docker.sh VERSION={like 0.0.1,0.1.0,1.0.0 etc}"
    exit 1
fi

docker tag bouncer:${VERSION} mwrona/bouncer:${VERSION}
docker push mwrona/bouncer:${VERSION}