/*
 * Copyright (c) 2018.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit http://ooon.me or mail to zhaihao@ooon.me
 */

package me.ooon.ospark
import com.typesafe.config.{Config => TC}
import me.ooon.base.syntax.config._
import me.ooon.ospark.core.Config

import scala.collection.mutable.ListBuffer

/**
  * package
  *
  * @author zhaihao
  * @version 1.0 2018/10/26 16:04
  */
package object biz {

  object Businesses extends Config {

    def businessFor(name: String): Option[Business] = all.find(_.name == name)
    def businessFor(id:   Int):    Option[Business] = all.find(_.id == id)

    val all: List[Business] = {

      config.opt[List[TC]]("businesses") match {
        case None => List.empty[Business]
        case Some(configList) =>
          var i  = 1
          val bf = ListBuffer.empty[Business]
          for (c <- configList) {
            val biz = Business(i, c.getString("name"), c.getString("logic"), c.opt[String]("desc"))
            i  += 1
            bf += biz
          }

          bf.toList
      }
    }

  }
}
