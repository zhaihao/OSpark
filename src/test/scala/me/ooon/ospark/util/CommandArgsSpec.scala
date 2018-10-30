/*
 * Copyright (c) 2018.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit http://ooon.me or mail to zhaihao@ooon.me
 */

package me.ooon.ospark.util
import me.ooon.base.test.BaseSpec

/**
  * CommandArgsSpec
  *
  * @author zhaihao
  * @version 1.0 2018/10/26 15:46
  */
class CommandArgsSpec extends BaseSpec {

  import CommandArgs._
  "help" in {
    val args = Array("--help")
    parse(args)
  }

  "list" in {
    val args = Array("list")
    parse(args)
  }

  "run" - {
    "-t ml logic" in {
      val args = Array("run", "-t", "ml", "logic")
      parse(args)
    }
  }

  private def parse(args: Array[String]) = parser.parse(args, CommandArgs()) match {
    case Some(c) => println(c)
    case None    => println("ops!")
  }
}
