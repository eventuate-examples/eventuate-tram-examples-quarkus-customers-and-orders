#! /bin/bash

set -e

export DATABASE=postgres
export MODE=wal
export EVENTUATEDATABASE=postgres

./_build-and-test-all.sh
