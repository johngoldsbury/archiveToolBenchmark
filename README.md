archiveToolBenchmark
====================
## What is it

##Requirements

* Linux only (Caliper does not currently function on windows)
* Java JDK 1.7
* Apache Maven installed  http://maven.apache.org/download.cgi
* JHOVE - https://github.com/gmcgath/jhove
* Google Caliper - git clone https://code.google.com/p/caliper/ 

##Installation
1. Install Maven
2. Install JHOVE and Caliper using 'mvn install' from terminal while in their respective directories.
3. Pull archiveToolBenchmark and place it anywhere.
4. Install archiveToolBenchmark using mvn install.
5. Wait while it pulls down dependencies for DROID 6.1.3 and Tika 1.7-SNAPSHOT (Requires internet connection).
6. Run mvn compile
7. Ready to execute Caliper Benchmarks via maven.
If the dependencies fail to be fulfilled for some reason automatically, you must download and install DROID (https://github.com/digital-preservation/droid) and Tika (https://github.com/apache/tika/) manually as with JHOVE and Caliper.

## Using archiveToolBenchmark
