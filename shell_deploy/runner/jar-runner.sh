#! /bin/sh

# 调用方式
# jar-runner.sh {start|stop|restart} <jar-path> <jar-name> <run-params>

#启动方法
start(){
 echo "$1 启动中。。。"
 now=`date "+%Y-%m-%d_%H:%M:%S"`
 #exec java -Xms1024m -Xmx2048m -jar $1 5 &
 mkdir -p "$1/deploy_log"
 VM_OPTS="-server -Xms128m -Xmx128m -Xss1M -XX:+UseParallelGC"
# -XX:PermSize=512m -XX:MaxPermSize=1g
# VM_OPTS="-Xmx6g -Xms6g -Xmn2g -XX:PermSize=512m -XX:MaxPermSize=1g -Xss1M -XX:+UseParNewGC -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70"

 java ${VM_OPTS} -jar $1/$2 $3 > result.log &
 tail -f result.log
}
#停止方法
stop(){
 echo "$1 停止中。。。"
 ps -ef|grep $1/$2|grep -v grep|awk '{print $2}'|while read pid
 do
    kill -9 $pid
 done
}

case "$1" in
start)
start  $2 $3 $4
;;
stop)
stop  $2 $3
;;
restart)
stop  $2 $3
start  $2 $3 $4
;;
*)
printf 'Usage: %s {start|stop|restart} <run_jar> \n' "$prog"
exit 1
;;
esac