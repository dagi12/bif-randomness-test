#!/usr/bin/env bash
sudo apt install dieharder
./gradlew clean build -x test
cd build/classes/java/main
java -cp . pl.edu.amu.wmi.bifrandomnesstest.lcg.LCGPrinter > lcg
java -cp . pl.edu.amu.wmi.bifrandomnesstest.mersennetwister.MTRandomPrinter > mt
dieharder -d diehard_opso -g 202 -f lcg
dieharder -d dieahrd_opso -g 202 -f mt