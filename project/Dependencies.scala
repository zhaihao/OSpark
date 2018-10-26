/*
 * Copyright (c) 2018.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit http://ooon.me or mail to zhaihao@ooon.me
 */

import sbt._

/**
  * Dependencies
  *
  * @author zhaihao
  * @version 1.0 2018/10/16 11:34
  */
object Dependencies {
  val spark_version = "2.3.2"
  val cdh_version   = "2.6.0-cdh5.6.0"

  lazy val base            = "me.ooon"          %% "base"                % "1.0.25"
  lazy val scopt           = "com.github.scopt" %% "scopt"               % "3.7.0"
  lazy val typesafe_config = "com.typesafe"     % "config"               % "1.3.3"
  lazy val graph_frame     = "org.graphframes"  %% "graphframes"         % "0.7.0-spark2.3-SNAPSHOT"
  lazy val mysql           = "mysql"            % "mysql-connector-java" % "5.1.47"

  lazy val log = Seq(
    "com.typesafe.scala-logging" %% "scala-logging"  % "3.9.0",
    "ch.qos.logback"             % "logback-classic" % "1.2.3"
  )

  val spark = Seq(
    "org.apache.spark" %% "spark-core"              % spark_version,
    "org.apache.spark" %% "spark-sql"               % spark_version,
    "org.apache.spark" %% "spark-yarn"              % spark_version,
    "org.apache.spark" %% "spark-hive"              % spark_version,
    "org.apache.spark" %% "spark-hive-thriftserver" % spark_version,
    "org.apache.spark" %% "spark-graphx"            % spark_version
  )

  val cdh = Seq(
    "org.apache.hadoop" % "hadoop-annotations"                % cdh_version,
    "org.apache.hadoop" % "hadoop-auth"                       % cdh_version,
    "org.apache.hadoop" % "hadoop-client"                     % cdh_version,
    "org.apache.hadoop" % "hadoop-common"                     % cdh_version,
    "org.apache.hadoop" % "hadoop-hdfs"                       % cdh_version,
    "org.apache.hadoop" % "hadoop-mapreduce-client-app"       % cdh_version,
    "org.apache.hadoop" % "hadoop-mapreduce-client-common"    % cdh_version,
    "org.apache.hadoop" % "hadoop-mapreduce-client-core"      % cdh_version,
    "org.apache.hadoop" % "hadoop-mapreduce-client-jobclient" % cdh_version,
    "org.apache.hadoop" % "hadoop-mapreduce-client-shuffle"   % cdh_version,
    "org.apache.hadoop" % "hadoop-yarn-api"                   % cdh_version,
    "org.apache.hadoop" % "hadoop-yarn-client"                % cdh_version,
    "org.apache.hadoop" % "hadoop-yarn-common"                % cdh_version,
    "org.apache.hadoop" % "hadoop-yarn-server-common"         % cdh_version,
    "org.mortbay.jetty" % "jetty"                             % "6.1.26.cloudera.4"
  )

  val exclude = Seq(
    ExclusionRule("com.sun.jersey"),
    ExclusionRule("com.sun.jersey.contribs"),
    ExclusionRule("javax.servlet.jsp", "jsp-api"),
    ExclusionRule("javax.servlet", "servlet-api"),
    ExclusionRule("org.mortbay.jetty", "servlet-api"),
    ExclusionRule("org.slf4j", "slf4j-log4j12"),
    ExclusionRule("log4j", "log4j")
  )
}
