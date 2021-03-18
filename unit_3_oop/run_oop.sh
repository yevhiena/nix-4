#!/bin/sh

mvn clean install
echo RUNNING PROGRAMM
java -jar application/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar
