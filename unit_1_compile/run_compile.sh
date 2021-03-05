#!/bin/sh
cd terminal
./terminal_run.sh

cd ../ant_compile
./ant_run.sh

cd ../maven_compile
./run_mvn.sh

