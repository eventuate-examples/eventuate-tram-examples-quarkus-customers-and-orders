#! /bin/bash

set -e

export DATABASE=postgres
export MODE=wal
export EVENTUATEDATABASE=postgresql
export QUARKUS_PROFILE=postgresql

./_build-and-test-all.sh
