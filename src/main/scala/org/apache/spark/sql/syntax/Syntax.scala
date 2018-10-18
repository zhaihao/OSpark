/*
 * Copyright (c) 2018.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit http://ooon.me or mail to zhaihao@ooon.me
 */

package org.apache.spark.sql.syntax

/**
  * Syntax
  *
  * @author zhaihao
  * @version 1.0 2018/10/17 17:21
  */
trait Syntax {
  object dataset extends ToDatasetOps
}
