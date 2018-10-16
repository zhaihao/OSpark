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

  lazy val typesafe_config = "com.typesafe"     % "config"      % "1.3.3"
  lazy val base            = "me.ooon"          %% "base"       % "1.0.25"
  lazy val spark_core      = "org.apache.spark" %% "spark-core" % spark_version % Provided
  lazy val spark_sql       = "org.apache.spark" %% "spark-sql"  % spark_version % Provided
  lazy val graph_frame     = "graphframes"      % "graphframes" % "0.6.0-spark2.3-s_2.11"
}
