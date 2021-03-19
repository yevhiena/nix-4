#!/bin/sh

mvn clean install
echo RUNNING PROGRAMM
java -jar appstring/target/appstring-1.0-SNAPSHOT-jar-with-dependencies.jar
