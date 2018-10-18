/*
 * Copyright (c) 2018.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit http://ooon.me or mail to zhaihao@ooon.me
 */

package me.ooon.ospark.core
import com.typesafe.scalalogging.StrictLogging
import org.apache.spark._

import scala.util.Properties

/**
  * StartScreen
  *
  * @author zhaihao
  * @version 1.0 2018/10/17 10:51
  */
object StartScreen extends Config with StrictLogging {

  def welcome = {
    logger.info(
      """
        |
        |Welcome to
        |   ____              __
        |  / __/__  ___ _____/ /__
        | _\ \/ _ \/ _ `/ __/  '_/
        |/___/ .__/\_,_/_/ /_/\_\   version %s %s
        |   /_/
        |   
        |Using Scala %s, %s, %s
        |
      """.stripMargin.format(SPARK_VERSION,
                             config.getString("env"),
                             Properties.versionString,
                             Properties.javaVmName,
                             Properties.javaVersion))
  }
}
