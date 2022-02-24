#!/bin/sh/
BASE_FILE=$(ls /deployments/)
echo $BASE_FILE
if [[ $BASE_FILE == "tomcat" ]]
then
    /deployments/tomcat/bin/startup.sh
else
    java -jar /deployments/*.jar
fi