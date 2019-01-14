#!/bin/bash

curl -v -X POST --header "content-type: application/json" --data '{"email": "admin", "password": "admin"}' http://127.0.0.1:8080/servermanager-core/api/v1/public/appUser/login

curl -v -X POST --header "content-type: application/json" --data '{"token": "77b176013e9d36d2"}' http://127.0.0.1:8080/servermanager-core/api/v1/public/gameMap/list
curl -v -X POST --header "content-type: application/json" --data '{"token": "77b176013e9d36d2", "gameTag": "cod2"}' http://127.0.0.1:8080/servermanager-core/api/v1/public/gameMap/list

curl -v -X POST --header "content-type: application/json" --data '{"token": "873c26d95953dea5", "gameTag": "cod2"}' http://127.0.0.1:8080/servermanager-core/api/v1/public/gametype/listPerGame
