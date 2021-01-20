#! /bin/bash

set -e

export DATABASE=postgres
export MODE=polling
export EVENTUATEDATABASE=postgres

./_build-and-test-all.sh
