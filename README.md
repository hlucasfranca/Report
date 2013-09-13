JSF - TDD Tutorial from Code ^ ^
===================
It means no any description just look on Code and run

Requirement
-----
1. Maven
2. Java SDK 7u25
3. Internet
4. Coffee or Beer


First Example
-----
JSF2 + Primefaces3.5 + TestNG + Mockito

1. Home loans calculation. (100% Code Coverage)


Fix build error
-----
1. The POM for com.thoughtworks.xstream:xstream:jar:1.4.2 is missing, no dependency information available

mvn install:install-file -Dfile=$PROJECT_HOME/libs/xstream-1.4.2.jar -DgroupId=com.thoughtworks.xstream -DartifactId=xstream -Dversion=1.4.2 -Dpackaging=jar