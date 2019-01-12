#!/bin/bash

curl -v -X POST --header "content-type: application/json" --data '{"email": "admin", "password": "admin"}' http://127.0.0.1:8080/servermanager-core/api/v1/public/appuser/login
curl -v -X POST --header "content-type: application/json" --data '{"token": "77b176013e9d36d2"}' http://127.0.0.1:8080/servermanager-core/api/v1/public/gamemap/list
curl -v -X POST --header "content-type: application/json" --data '{"token": "77b176013e9d36d2", "gameTag": "cod2"}' http://127.0.0.1:8080/servermanager-core/api/v1/public/gamemap/list

