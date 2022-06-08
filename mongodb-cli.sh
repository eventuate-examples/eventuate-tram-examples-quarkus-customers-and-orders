#! /bin/bash

docker run  $* --network=eventuate-teqco_default --rm mongo:4.2.12 sh -c "exec /usr/bin/mongo --host mongodb customers_and_orders"
