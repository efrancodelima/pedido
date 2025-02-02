#!/bin/bash

export H2_USERNAME=sa
export H2_PASSWORD=password

mvn spring-boot:run -Dspring-boot.run.profiles=local