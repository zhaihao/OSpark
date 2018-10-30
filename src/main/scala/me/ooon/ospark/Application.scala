/*
 * Copyright (c) 2018.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit http://ooon.me or mail to zhaihao@ooon.me
 */

package me.ooon.ospark
import com.typesafe.scalalogging.StrictLogging
import me.ooon.ospark.biz.Businesses
import me.ooon.ospark.core.{Config, StartScreen}
import me.ooon.ospark.util.CommandArgs._
import me.ooon.ospark.util.{AppType, CommandArgs}

/**
  * Application
  *
  * @author zhaihao
  * @version 1.0 2018/10/16 10:58
  */
object Application extends StrictLogging with Config {

  def main(args: Array[String]): Unit = {
    StartScreen.welcome
//    processArgs(Array("run","-t","graph","Users"))
    processArgs(Array("--help"))

  }

  def processArgs(args: Array[String]) = {
    val commandArgsOption = parser.parse(args, CommandArgs())
    if (commandArgsOption.isEmpty) {
      logger.error("command args can't be parsed")
      sys.exit(-1)
    }

    val commandArgs = commandArgsOption.get
    if (commandArgs.command == "list") {
      listCommand
      sys.exit(0)
    } else if (commandArgs.command == "run") {
      runCommand(commandArgs)
    } else {
      logger.error("未知的 command")
    }

  }

  def runCommand(commandArgs: CommandArgs) = {
    commandArgs.`type` match {
      case AppType.batch     =>
      case AppType.ml        =>
      case AppType.streaming =>
      case AppType.graph     =>
    }
  }

  def listCommand = {
    val strings = Businesses.all.map(_.pretty).mkString(start = "\t", sep = "\n\t", end = "")
    logger.info("已注册的业务逻辑：" + "\n" + strings)
  }

}
