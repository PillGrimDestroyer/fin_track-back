#!/bin/sh

docker-compose down

sudo rm -rf ./volumes

docker-compose up -d
