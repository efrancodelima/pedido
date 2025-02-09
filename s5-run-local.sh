#!/bin/bash

export H2_USERNAME=sa
export H2_PASSWORD=password
export URL_SERV_PAGAMENTO="http://44.195.69.39:8081/"
export URL_SERV_PRODUCAO="http://107.22.58.106:8082/"

mvn spring-boot:run -Dspring-boot.run.profiles=test
