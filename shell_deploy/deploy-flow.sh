#! /bin/sh

# 调用方式
# ./deploy.sh <module> <version> <run-params> <host>

cd $WORKSPACE
source ~/.bash_profile

java -version
echo $JAVA_HOME



deploy_project=$1
deploy_version=$2
deploy_params=$3
deploy_ssh_hosts=$4
deploy_host_port=22


deploy_jar="$deploy_project-$deploy_version.jar"
upload_path="$WORKSPACE/$deploy_project/target/$deploy_jar"
deploy_dir="/data/apps"

# 备份目录
backup_dir="$deploy_dir/backup/$deploy_project"

# 备份文件后缀
last_backup_suffix="last_backup"


upgrade_app() {
    echo -e "发布app"

    echo -e "开始编译.....\n"

    build_app

    echo -e "编译完成\n"

    echo -e "循环启动app, 所有服务器: ${deploy_ssh_hosts}\n"

    startup_app ${deploy_ssh_hosts}

    echo "所有服务器启动app完成"

}

build_app() {
    ./gradlew clean
    ./gradlew build
}

startup_app() {

    echo -e "开始启动app......\n"

    # split host
    deploy_ssh_hosts_arr=(${1//,/ })

    for host in ${deploy_ssh_hosts_arr[*]}; do
        startup_host_app $host
    done

}

startup_host_app () {

    echo -e "当前主机: $1\n"

    deploy_ssh_host="$1"

    echo "准备备份当前主机app"
    # 备份当前工程
    backup_host_app $deploy_ssh_host

    ssh $deploy_ssh_host "mkdir -p $deploy_dir"
    scp $WORKSPACE/shell_deploy/runner/spring-boot.sh $deploy_ssh_host:$deploy_dir/spring-boot.sh
    ssh $deploy_ssh_host "chmod a+x $deploy_dir/spring-boot.sh"

    echo "正在停止当前主机运行的app......"

    # 停止当前工程
    ssh $deploy_ssh_host "cd $deploy_dir && ./spring-boot.sh stop $deploy_dir $deploy_jar"

    echo "正在删除当前主机运行的app......"

    # 删除当前工程
    ssh $deploy_ssh_host "cd $deploy_dir && rm -f $deploy_project-*.jar"

    echo "正在上传新版本的app包......"

    # 上传新的工程
    scp $upload_path $deploy_ssh_host:$deploy_dir/$deploy_jar

    echo "开始启动新版本的app......"

    # 启动工程
    ssh $deploy_ssh_host "cd $deploy_dir && ./spring-boot.sh start $deploy_dir $deploy_jar $deploy_params"

    #判断工程是否启动成功
    if [ $? -eq 0 ];then
        echo -e "启动成功, 主机地址: ${deploy_ssh_host}\n"
    else
        # 如果启动失败, 回退上个备份版本
        rollback_host_app $deploy_ssh_host
    fi


}


check_project_exist() {

    ssh_host="$1"

    projet_name=$(ssh ${ssh_host} "cd $deploy_dir && find . -maxdepth 1 -name ${deploy_project}-*.jar")

    echo -e "查找当前运行的app: ${projet_name}\n"

    if [ "$projet_name" = "" ];then
      echo -e "没有找到主机app\n"
      return 1
    else
      echo "正在备份主机app......"
      return 0
    fi
}


backup_host_app() {

    deploy_ssh_host="$1"

    echo "备份目录: ${backup_dir}"

    echo "备份文件后缀: ${last_backup_suffix}"

    # 根据工程名创建备份目录
    ssh $deploy_ssh_host "mkdir -p $backup_dir"

    # 判断工程jar是否存在
    check_project_exist $deploy_ssh_host

    if [ $? -eq 0 ];then

        # 修改上次备份的文件名
        ssh $deploy_ssh_host "cd $backup_dir && rename .jar.last_backup .jar *.last_backup "

        # 拿到需要备份的工程名
        result_project_name=$(ssh $deploy_ssh_host "cd $deploy_dir && echo `basename $deploy_project-*.jar`")

        # echo "当前主机需要备份的app名: $result_project_name"

        # 备份当前工程 cy-boot-uc-1.0+2016-08-17_11:16:39.jar.last_backup
        ssh $deploy_ssh_host "cd $deploy_dir && cp -v ${result_project_name} $backup_dir/`basename ${result_project_name} .jar`+`date  "+%Y-%m-%d_%H:%M:%S"`.jar.${last_backup_suffix}"

        # echo -e "备份当前主机运行的app完成\n"

    else
        echo -e "当前主机app不存在, 无需备份\n"
    fi


}

rollback_host_app() {

    deploy_ssh_host="$1"

    echo "发布失败, 回退上次备份版本, 当前主机地址:${deploy_ssh_host}"

    # 停止当前工程
    ssh $deploy_ssh_host "cd $deploy_dir && ./spring-boot.sh stop $deploy_dir $deploy_jar"

    # 删除当前发布的工程
    ssh $deploy_ssh_host "cd $deploy_dir && rm -f $deploy_jar"

    # 拿到上次备份的工程名
    result_last_backup_project_name=$(ssh $deploy_ssh_host "cd $backup_dir && echo `basename $deploy_project-*.jar.$last_backup_suffix`")

    echo "上次备份的app名: ${result_last_backup_project_name}"

    project_name_arr=(${result_last_backup_project_name//+/ })

    echo "还原之后的app名: ${project_name_arr[0]}"

    # mv 上次备份的工程到启动目录 并去掉备份后缀
    ssh $deploy_ssh_host "cd $backup_dir && mv ${result_last_backup_project_name} $deploy_dir/${project_name_arr[0]}.jar"

    # 启动上次备份的工程
    ssh $deploy_ssh_host "source /etc/profile && cd $deploy_dir && ./spring-boot.sh start ${deploy_dir} ${project_name_arr[0]}.jar ${deploy_params}"
}

# 调用更新脚本
upgrade_app
