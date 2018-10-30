/*
 * Copyright (c) 2018.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit http://ooon.me or mail to zhaihao@ooon.me
 */

package me.ooon.ospark.core
import com.typesafe.config.ConfigFactory

/**
  * Config
  *
  * @author zhaihao
  * @version 1.0 2018/10/16 17:58
  */
trait Config {
  lazy val config = ConfigFactory.load()
  lazy val env    = Env.withName(config.getString("env"))
}

object Env extends Enumeration {
  type Env = Value

  val prod, test, stage, dev = Value
}
