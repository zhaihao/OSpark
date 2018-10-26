import Dependencies._

organization := "me.ooon"
name         := "OSpark"
scalaVersion := "2.11.12"

// 限制 scala 编译警告信息
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")

// 配置第三方依赖仓库地址
resolvers += "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
resolvers += "cloudera repo" at "https://repository.cloudera.com/artifactory/cloudera-repos/"
resolvers += Resolver.url("ooon ivy repo", url("http://repo.ooon.me/release"))(
  Resolver.ivyStylePatterns)

externalResolvers := Resolver.combineDefaultResolvers(resolvers.value.toVector, mavenCentral = true)

libraryDependencies ++= spark
libraryDependencies ++= cdh
libraryDependencies ++= log
libraryDependencies ++= Seq(base, typesafe_config, graph_frame, mysql)

excludeDependencies ++= Seq(
  ExclusionRule("com.sun.jersey"),
  ExclusionRule("com.sun.jersey.contribs"),
  ExclusionRule("javax.servlet.jsp", "jsp-api"),
  ExclusionRule("javax.servlet", "servlet-api"),
  ExclusionRule("org.mortbay.jetty", "servlet-api"),
  ExclusionRule("org.slf4j", "slf4j-log4j12"),
  ExclusionRule("log4j", "log4j")
)

dependencyOverrides ++= cdh // 强制 cdh 版本

// 多 env 支持
lazy val renameEnvConfigTask = taskKey[Unit]("rename env config")

renameEnvConfigTask := {
  val confFile = buildEnv.value match {
    case BuildEnv.Development => "dev.conf"
    case BuildEnv.Test        => "test.conf"
    case BuildEnv.Stage       => "stage.conf"
    case BuildEnv.Production  => "prod.conf"
  }

  val log = streams.value.log
  log.info(s"Moving $confFile to application.conf")
  val from = (resourceDirectory in Compile).value / confFile
  val to   = (classDirectory in Compile).value / "application.conf"
  IO.copyFile(from, to)
  log.success("Done building env")
}

copyResources in Compile := {
  renameEnvConfigTask.value
  (copyResources in Compile).value
}

// 打包 spark archive 依赖
lazy val sparkArchive = taskKey[Unit]("zip spark lib archive for spark.yarn.archive")
sparkArchive := {
  val log = streams.value.log
  log.info("Archiving dependency jars")
  val jars: Seq[(File, String)] =
    (fullClasspath in Runtime).value.files.map(f => (f, f.getName)).filter(_._2.endsWith(".jar"))

  IO.zip(jars, (baseDirectory in Compile).value / (name.value + "_lib.zip"))
  log.info("Done zipping lib")
}
