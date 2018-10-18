/*
 * Copyright (c) 2018.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit http://ooon.me or mail to zhaihao@ooon.me
 */

package me.ooon.ospark
import com.typesafe.scalalogging.StrictLogging
import me.ooon.ospark.core.{Config, Spark, StartScreen}

/**
  * Application
  *
  * @author zhaihao
  * @version 1.0 2018/10/16 10:58
  */
object Application extends Spark with Config with StrictLogging {

  def main(args: Array[String]): Unit = {
    StartScreen.welcome
    logger.info(spark.version) // todo remove
    import org.graphframes.examples.Graphs
    val g = Graphs.friends
    import org.apache.spark.sql.syntax.dataset._
    g.triplets.log(truncate = 0)
    g.pageRank.maxIter(10).run().triplets.log(truncate = 0)
  }

}
