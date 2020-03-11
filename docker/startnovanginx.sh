#!/bin/sh
# Start NOVA-Dev NGINX docker container by first building container and then running

docker build -t "nova-dev:1.0" .
docker run -it --rm -d --name "nova" -p 80:80 -v "/Users/jakehansen/Documents/nova_repo/www":/usr/share/nginx/html:ro nova-dev:1.0