#! /bin/bash

set -e

export DATABASE=postgres
export MODE=wal
export EVENTUATEDATABASE=postgresql

./_build-and-test-all.sh
