#! /bin/bash

set -e

export DATABASE=postgres
export MODE=polling
export EVENTUATEDATABASE=postgresql

./_build-and-test-all.sh
