/*
 * Copyright (c) 2018.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit http://ooon.me or mail to zhaihao@ooon.me
 */

package me.ooon.ospark.core
import me.ooon.base.syntax.config._
import org.apache.spark.sql.SparkSession

/**
  * Spark
  *
  * @author zhaihao
  * @version 1.0 2018/10/16 17:19
  */
trait Spark {

  import Spark._

  lazy val spark = {
    if (config.opt[String]("env").contains("dev")) {
      val c = config.opt[com.typesafe.config.Config]("spark").get

      SparkSession
        .builder()
        .master(c.getOrElse[String]("master", "local[*]"))
        .appName(c.getOrElse[String]("appName", "spark"))
        .getOrCreate()
    } else {
      SparkSession.builder().enableHiveSupport().getOrCreate()
    }
  }

}

/**
  * 不要使用 Spark.config 获取配置对象
  */
private[core] object Spark extends Config
