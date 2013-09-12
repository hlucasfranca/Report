JSF
===================

Fix build error
-----
1. The POM for com.thoughtworks.xstream:xstream:jar:1.4.2 is missing, no dependency information available

mvn install:install-file -Dfile=$PROJECT_HOME/libs/xstream-1.4.2.jar -DgroupId=com.thoughtworks.xstream -DartifactId=xstream -Dversion=1.4.2 -Dpackaging=jar