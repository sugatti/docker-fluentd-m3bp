#!/bin/bash
PRJ_HOME=/workspace/example-m3bp

cd ${PRJ_HOME}
./gradlew -g /workspace/lib assemble
rm -rf $ASAKUSA_HOME
mkdir $ASAKUSA_HOME
cd $ASAKUSA_HOME
tar xvf ${PRJ_HOME}/build/asakusafw-example-m3bp.tar.gz
java -jar tools/bin/setup.jar
