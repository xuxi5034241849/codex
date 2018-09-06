#!/usr/bin/env bash

#
# Startup script for a spring boot project
#
# chkconfig: - 84 16
# description: spring boot project

# Source function library.
[ -f "/etc/rc.d/init.d/functions" ] && . /etc/rc.d/init.d/functions
[ -z "$JAVA_HOME" -a -x /etc/profile.d/java.sh ] && . /etc/profile.d/java.sh


# the name of the project, will also be used for the war file, log file, ...
PROJECT_NAME=$3
# the user which should run the service
SERVICE_USER=root
# base directory for the spring boot jar
SPRINGBOOTAPP_HOME=$2
export SPRINGBOOTAPP_HOME

# the spring boot jar-file
SPRINGBOOTAPP_JAR=$3

# spring boot app 启动参数
SPRINGBOOTAPP_PARAMS=$4

# java executable for spring boot app, change if you have multiple jdks installed
SPRINGBOOTAPP_JAVA=java

# JVM 运行参数
VM_OPTS="-server -Xms128m -Xmx128m -Xss1M -XX:+UseParallelGC"
# -XX:PermSize=512m -XX:MaxPermSize=1g
# VM_OPTS="-Xmx6g -Xms6g -Xmn2g -XX:PermSize=512m -XX:MaxPermSize=1g -Xss1M -XX:+UseParNewGC -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70"
 MONITOR_OPTS="-Dcom.sun.management.jmxremote.port=9998
        -Dcom.sun.management.jmxremote.authenticate=false
        -Dcom.sun.management.jmxremote.ssl=false
        -Djava.rmi.server.hostname=115.159.183.83"

# spring boot log-file
LOG="./$SPRINGBOOTAPP_JAR.log"

LOCK="./$SPRINGBOOTAPP_JAR.lock"

cd "$SPRINGBOOTAPP_HOME"

RETVAL=0

pid_of_spring_boot() {
    pgrep -f "java.*$SPRINGBOOTAPP_JAR"
}

success(){
    echo $*
    exit 0
}

failure(){
    echo $*
    exit 1
}

start() {
    echo $"Starting $SPRINGBOOTAPP_JAR ..."

    # check has running
    status
    hasRun=$?
    [ $hasRun = 0 ] && failure "Started $SPRINGBOOTAPP_JAR Failed: $SPRINGBOOTAPP_JAR already running."

    # 标记日志输出行数
    [ -e "$LOG" ] && cnt=`wc -l "$LOG" | awk '{ print $1 }'` || cnt=0
    ((cnt++))

    echo "jar 启动参数 SPRINGBOOTAPP_PARAMS:${SPRINGBOOTAPP_PARAMS}"

    echo "sssssssssssssssssss $SPRINGBOOTAPP_JAVA $VM_OPTS -jar \"$SPRINGBOOTAPP_JAR\" $SPRINGBOOTAPP_PARAMS"

    source /etc/profile

    # 运行 jar 文件
    bash -c "nohup $SPRINGBOOTAPP_JAVA $VM_OPTS -jar \"$SPRINGBOOTAPP_JAR\" $SPRINGBOOTAPP_PARAMS  >> \"$LOG\" 2>&1 &"

    echo $PATH
    # 通过日志检查输出状态,并输出日志
    outputCount=0
    allCount=0
    startupError=0
    while { pid_of_spring_boot > /dev/null ; } &&
        ! { tail --lines=+$cnt "$LOG" | grep -q ' Started .* in' ; } ; do
        sleep 1
        allCount="`tail --lines=+$cnt "$LOG" | wc -l`"

        #echo "cnt: ${cnt}"

        #echo "allCount:${allCount}"

        ((showCount=allCount-outputCount))

       # echo "showCount:${showCount}"

        tail --lines=+$cnt "$LOG" | tail --lines=+$outputCount | head -n $showCount
        outputCount=$allCount

        #echo "outputCount: ${outputCount}"

        # 启动失败跳出循环
        tail --lines=+$cnt "$LOG" | grep -q 'ERROR .* Application startup failed'
        startupError=$?

        #echo "startupError:${startupError}"

        [ $startupError = 0 ] && break
    done

    # 输出最后的日志
    sleep 3
    allCount="`tail --lines=+$cnt "$LOG" | wc -l`"
    ((showCount=allCount-outputCount))
    tail --lines=+$cnt "$LOG" | tail --lines=+$outputCount | head -n $showCount
    outputCount=$allCount

    # 通过日志判断是否有启动失败
    [ $startupError = 0 ] && failure "Starting $SPRINGBOOTAPP_JAR Failed"

    # 检测是否启动成功
    pid_of_spring_boot > /dev/null
    RETVAL=$?
    [ $RETVAL = 0 ] && touch "$LOCK"
    [ $RETVAL = 0 ] && success "Started $SPRINGBOOTAPP_JAR Success" || failure "Starting $SPRINGBOOTAPP_JAR Failed"
}

stop() {

    echo -n "Stopping $PROJECT_NAME: "

    pid=`pid_of_spring_boot`
    [ -n "$pid" ] && kill $pid
    RETVAL=$?
    cnt=10
    while [ $RETVAL = 0 -a $cnt -gt 0 ] &&
        { pid_of_spring_boot > /dev/null ; } ; do
            sleep 1
            ((cnt--))
            echo -n " ."
    done
    echo " OK."

    pid_of_spring_boot > /dev/null
    RETVAL=$?

    [ $RETVAL != 0 ] && rm -f "$LOCK"
    [ $RETVAL != 0 ] && success "Stopped $SPRINGBOOTAPP_JAR Success\n" || failure "Stopped $SPRINGBOOTAPP_JAR Failed"
}

status() {

    pid=`pid_of_spring_boot`
    if [ -n "$pid" ]; then
        echo "$PROJECT_NAME (pid $pid) is running..."
        return 0
    fi
    if [ -f "$LOCK" ]; then
        echo $"${base} dead but subsys locked"
        return 2
    fi
    echo "$PROJECT_NAME is stopped"
    return 3
}

# See how we were called.
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    status)
        status
        ;;
    restart)
        stop
        start
        ;;
    *)
        echo $"Usage: $0 {start|stop|restart|status}"
        exit 1
esac

exit $RETVAL