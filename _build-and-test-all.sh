#! /bin/bash

set -e

dockerall="./gradlew ${DATABASE?}${MODE?}Compose"

${dockerall}Down

./gradlew -x :end-to-end-tests:test build

${dockerall}Up

#Testing db cli

if [ "${DATABASE}" == "mysql" ]; then
  echo 'show databases;' | ./mysql-cli.sh -i
elif [ "${DATABASE}" == "postgres" ]; then
  echo '\l' | ./postgres-cli.sh -i
elif [ "${DATABASE}" == "mssql" ]; then
  ./mssql-cli.sh "SELECT name FROM master.sys.databases;"
else
  echo "Unknown Database"
  exit 99
fi

echo 'show dbs' |  ./mongodb-cli.sh -i

./gradlew :end-to-end-tests:cleanTest :end-to-end-tests:test

${dockerall}Down
