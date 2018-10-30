/*
 * Copyright (c) 2018.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit http://ooon.me or mail to zhaihao@ooon.me
 */

package me.ooon.ospark.biz

/**
  * Business
  *
  * @author zhaihao
  * @version 1.0 2018/10/29 14:43
  */
case class Business(id: Int, name: String, logic: String, desc: Option[String]) {
  def pretty = s"id\t\t$name\t\t$logic\t\t${desc.getOrElse("")}"
}
