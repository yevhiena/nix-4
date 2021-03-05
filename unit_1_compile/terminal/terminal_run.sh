#!/bin/sh
rm -R build
echo TERMINAL COMPILE OUTPUT:
javac -sourcepath ./src -d build/classes -cp ./libs/commons-lang3-3.11.jar:./libs/commons-collections4-4.4.jar src/com/alevel/flowers/Flowers.java src/com/alevel/flowerscounter/FlowersCounter.java src/com/alevel/App.java
java -cp build/classes/:./libs/commons-lang3-3.11.jar/:./libs/commons-collections4-4.4.jar:. com.alevel.App