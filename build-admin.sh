#!/bin/bash

datename=$(date +%Y%m%d-%H%M%S)
#appserver=l-db-server1.prod.hb1.ecs
appserver=/home/wanghongen/test
appname=spring-boot-demo
appjar=$appname.jar
buildjar=target/spring-boot-demo-1.0.jar

set -x
mvn package -Dmaven.test.skip=true

#ssh -T $appserver <<EOFB
#      cd /srv/
#      pkill -f $appjar
#      if [ -d "$appname" ]; then
#        mv ./$appname ./$appname.bak.$datename
#        # rm -rf ./$appname
#      fi
#      mkdir ./$appname
#EOFB

#scp $buildjar l-db-server1.prod.hb1.ecs:/srv/$appname/$appjar
cp $buildjar $appserver/$appname/$appjar

echo '>>> starting'
#ssh -T $appserver <<EOFA
    cd $appserver/$appname/
#    nohup java -Dfile.encoding=UTF-8 -Xms4g -Xmx4g -server -XX:SurvivorRatio=8 -XX:MaxMetaspaceSize=512M -XX:+UseConcMarkSweepGC -XX:CompressedClassSpaceSize=512M -XX:MaxTenuringThreshold=5 -XX:CMSInitiatingOccupancyFraction=70 -Dlogs=logs -verbose:gc -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:logs/gc.log.$(date +%Y%m%d%H%M) -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=1 -XX:GCLogFileSize=512M -jar $appjar > nohub.log 2>&1 &
    nohup java -Dfile.encoding=UTF-8 -jar $appjar > nohub.log 2>&1 &
#EOFA

sleep 30
set +x

[ $? == 0 ] || exit 1

#monitor port
#set +x
#for ((i=0;i<40;i++))
#do
#    sleep 5
#    nc -v -w 1 ${host} ${listenport}
#    if [ $? = 0 ];then
#        exit 0
#    fi
#done
#set -x

#echo "can not connect to ${host}:${listenport}, please check startup log file!"
#echo "print startup log file last 500 lines..."
#ssh root@${host} "
#tail -500 ${remote_dir}/startup.log
#"
#exit 1
