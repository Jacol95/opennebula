#!/bin/bash

if [ "$1" != "-web" ]; then
  cd ../optilion-db
  git pull
  mvn clean install
  cd ../optilion-commons
  git pull
  mvn clean install
fi
cd ../optilion-web
git pull
mvn clean tomcat7:redeploy -DskipTests
