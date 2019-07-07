@echo off
mvn install:install-file -DgroupId=com.xingyun -DartifactId=smart-mybatis3 -Dversion=1.0-RELEASE -Dpackaging=jar -Dfile=smart-mybatis3-1.0-RELEASE.jar
@echo install success
@pause