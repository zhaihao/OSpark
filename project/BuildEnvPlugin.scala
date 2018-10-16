/*
 * Copyright (c) 2018.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit http://ooon.me or mail to zhaihao@ooon.me
 */

import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin

/**
  * BuildEnvPlugin
  *
  * @author zhaihao
  * @version 1.0 2018/10/16 11:34
  */
object BuildEnvPlugin extends AutoPlugin {

  // make sure it triggers automatically
  override def trigger  = AllRequirements
  override def requires = JvmPlugin

  object autoImport {
    object BuildEnv extends Enumeration {
      val Production, Stage, Test, Development = Value
    }

    val buildEnv = settingKey[BuildEnv.Value]("the current build environment")
  }
  import autoImport._

  override def projectSettings: Seq[Setting[_]] = Seq(
    buildEnv := {
      sys.props
        .get("env")
        .orElse(sys.env.get("BUILD_ENV"))
        .flatMap {
          case "prod"  => Some(BuildEnv.Production)
          case "stage" => Some(BuildEnv.Stage)
          case "test"  => Some(BuildEnv.Test)
          case "dev"   => Some(BuildEnv.Development)
          case _       => None
        }
        .getOrElse(BuildEnv.Development)
    },
    // give feed back
    onLoadMessage := {
      // depend on the old message as well
      val defaultMessage = onLoadMessage.value
      val env            = buildEnv.value
      s"""|$defaultMessage
          |Running in build environment: $env""".stripMargin
    }
  )

}
