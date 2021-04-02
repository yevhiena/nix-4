#!/bin/sh

mvn clean install
echo RUNNING PROGRAMM
java -jar target/crud-jar-with-dependencies.jar