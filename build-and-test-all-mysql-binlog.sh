#! /bin/bash

set -e

export DATABASE=mysql
export MODE=binlog
export EVENTUATEDATABASE=mysql

./_build-and-test-all.sh
