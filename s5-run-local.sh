#!/bin/bash

export H2_USERNAME=sa
export H2_PASSWORD=password
export URL_SERV_PAGAMENTO="http://localhost:8081/"
export URL_SERV_PRODUCAO="http://localhost:8082/"

mvn spring-boot:run -Dspring-boot.run.profiles=test
