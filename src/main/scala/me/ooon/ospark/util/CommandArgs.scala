/*
 * Copyright (c) 2018.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit http://ooon.me or mail to zhaihao@ooon.me
 */

package me.ooon.ospark.util

import scala.language.postfixOps
import me.ooon.base.scopt

/**
  * CommandArgs
  *
  * @author zhaihao
  * @version 1.0 2018/10/26 15:16
  */
case class CommandArgs(`type`:  AppType.AppType = AppType.batch,
                       command: String = "",
                       biz:     String = "")

object CommandArgs {
  implicit val typeReader: scopt.Read[AppType.Value] = scopt.Read.reads(AppType withName)

  val parser = new scopt.OptionParser[CommandArgs]("OSpark") {
    head("OSpark", "1.0.0")

    note("\nOSpark 基于 spark 开发的\n")

    help("help").text("显示使用说明\n")

    cmd("list")
      .action {
        (_, c) => c.copy(command = "list")
      }
      .text("  获取已注册的业务逻辑\n")

    cmd("run")
      .action {
        (_, c) => c.copy(command = "run")
      }
      .text("  执行某个注册业务逻辑\n")
      .children(
        opt[AppType.Value]('t', "type")
          .required()
          .action {
            (x, c) => c.copy(`type` = x)
          }
          .text(s"程序类型参数，支持的计算类型有：${AppType.values.mkString("\"", "\", \"", "\"")}"),
        arg[String]("<business>")
          .action {
            (x, c) => c.copy(biz = x)
          }
          .required()
          .text("程序执行的业务逻辑，可以通过 list 命令获取已注册的业务逻辑")
      )

  }
}

object AppType extends Enumeration {
  type AppType = Value

  val ml, batch, streaming, graph = Value
}
