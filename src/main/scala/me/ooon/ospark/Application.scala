/*
 * Copyright (c) 2018.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit http://ooon.me or mail to zhaihao@ooon.me
 */

package me.ooon.ospark
import com.typesafe.scalalogging.StrictLogging
import me.ooon.ospark.core.{Config, Spark, StartScreen}
import org.apache.spark.sql.SaveMode
import org.graphframes.GraphFrame

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
    import org.apache.spark.sql.syntax.dataset._

    val e = spark.sql(
      "SELECT phone1 as src,phone2 as dst FROM rdm_fin.graph_sample_phone GROUP BY phone1,phone2")
    val g   = GraphFrame.fromEdges(e)
    val df1 = g.inDegrees
    val df2 = g.outDegrees

    df1.log(truncate = 0)
    df2.log(truncate = 0)

    df1
      .join(df2, Seq("id"), "full")
      .write
      .mode(SaveMode.Overwrite)
      .format("parquet")
      .saveAsTable("vdm_fin.zhaihao_result")

  }

}
