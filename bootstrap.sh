#!/bin/sh

echo "Starting bootstrap"
containername=$(hostname)
echo "Container name: ${containername}"
echo "Monitored by AppDynamics: $ENABLE_MONITORING"
echo "$SPRING_PROFILES_ACTIVE: $SPRING_PROFILES_ACTIVE"
echo "APPDYNAMICS_MONITORING $APPDYNAMICS_MONITORING"
echo "Java version running in this container: $(java --version)"

if [ "$APPDYNAMICS_MONITORING" == "enabled" ]
then
  java ${JAVA_OPTS} -Xms1020m -Xmx1020m -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -Dappdynamics.agent.applicationName=$MONITORING_GROUP -Dappdynamics.agent.tierName=$APPLICATION_NAME -Dappdynamics.agent.nodeName=\"${containername}\" -javaagent:/var/appdynamics/javaagent.jar -jar /app/app.jar
else
  java ${JAVA_OPTS} -Xms1020m -Xmx1020m -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar /app/app.jar
fi
