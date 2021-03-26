#!/bin/sh

mvn clean install
echo RUNNING PROGRAMM
java -jar app/target/app-1.0-SNAPSHOT-jar-with-dependencies.jar