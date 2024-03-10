#!/bin/bash

ENDPOINT="http://localhost:8080/swagger-ui/index.html"

NUM_REQUESTS=1000

for ((i=1; i<=$NUM_REQUESTS; i++)); do
    echo "Fazendo solicitação $i para $ENDPOINT"
    curl -sS $ENDPOINT > /dev/null
    sleep 0.1
done
