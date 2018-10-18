#!/usr/bin/env bash

#  --conf spark.dynamicAllocation.enabled=true \
#    --conf spark.shuffle.service.enabled=true \
#    --conf spark.dynamicAllocation.minExecutors=2 \
#    --conf spark.dynamicAllocation.maxExecutors=100 \
#    --conf spark.dynamicAllocation.executorIdleTimeout=300s \

spark-submit \
    --master yarn \
    --class me.ooon.ospark.Application \
    --keytab /opt/keytab/zhaihao.keytab \
    --principal zhaihao@U51.COM \
    --deploy-mode cluster \
    --driver-memory 8g \
    --driver-cores 4 \
    --executor-memory 8g \
    --executor-cores 4 \
    --num-executors 10 \
    --name "OSpark" \
    --conf spark.app.name="OSpark" \
    --conf spark.default.parallelism=80 \
    --conf spark.sql.shuffle.partitions=80 \
    --conf "spark.driver.extraJavaOptions=-XX:MaxMetaspaceSize=512M -Xmn3g -XX:+UseParNewGC -XX:+ParallelRefProcEnabled -XX:+CMSParallelRemarkEnabled -XX:+UseConcMarkSweepGC -XX:ParallelCMSThreads=6  -XX:+CMSClassUnloadingEnabled -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=64" \
    --conf "spark.executor.extraJavaOptions=-XX:MaxMetaspaceSize=512M -Xmn3g -XX:+UseParNewGC -XX:+ParallelRefProcEnabled -XX:+CMSParallelRemarkEnabled -XX:+UseConcMarkSweepGC -XX:ParallelCMSThreads=6 -XX:+CMSClassUnloadingEnabled -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=64" \
    --conf spark.yarn.archive=hdfs://nameservice1/user/zhaihao/OSpark_lib.zip \
    --conf spark.ui.showConsoleProgress=false \
    --conf spark.speculation=true \
    --conf spark.yarn.executor.memoryOverhead=2G \
    --conf spark.yarn.driver.memoryOverhead=2G \
    --conf mapreduce.job.complete.cancel.delegation.tokens=false \
    --conf spark.hadoop.fs.hdfs.impl.disable.cache=true \
    --conf spark.hadoop.dfs.client.block.write.locateFollowingBlock.retries=6 \
    --conf spark.hadoop.hive.exec.max.dynamic.partitions=20000 \
    --conf spark.yarn.appMasterEnv.JAVA_HOME=/usr/java/jdk1.8.0_74/ \
    --conf spark.executorEnv.JAVA_HOME=/usr/java/jdk1.8.0_74/ \
    --queue root.fdsp_prod_etl.default \
    --files "/opt/spark/conf/hive-site.xml,/opt/hadoop/etc/hdfs-site.xml,/opt/hadoop/etc/yarn-site.xml,./logback.xml" \
    ospark_2.11-1.0.0-SNAPSHOT.jar
