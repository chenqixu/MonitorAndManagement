#!/bin/bash
#cd /home/zyh/MonitorAndManagement-WebUI/bin
#sh shutdown.sh
cd /home/zyh/webapps
rm -rf MonitorAndManagement-WebUI
mkdir MonitorAndManagement-WebUI
jar -xvf MonitorAndManagement-WebUI.war
mv WEB-INF MonitorAndManagement-WebUI/
mv META-INF MonitorAndManagement-WebUI/
mv css MonitorAndManagement-WebUI/
mv js MonitorAndManagement-WebUI/
mv *.jsp MonitorAndManagement-WebUI/
cp log4j.properties MonitorAndManagement-WebUI/WEB-INF/classes/
#cd /home/zyh/MonitorAndManagement-WebUI/bin
#sh startup.sh
