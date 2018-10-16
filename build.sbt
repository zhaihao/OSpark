import Dependencies._

organization := "me.ooon"
name         := "OSpark"
scalaVersion := "2.11.12"

// 限制 scala 编译警告信息
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")

// 配置第三方依赖仓库地址
resolvers += "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
resolvers += "spark-packages" at "https://dl.bintray.com/spark-packages/maven"
resolvers += Resolver.url("ooon ivy repo", url("http://repo.ooon.me/release"))(Resolver.ivyStylePatterns)

externalResolvers := Resolver.combineDefaultResolvers(resolvers.value.toVector, mavenCentral = true)

libraryDependencies ++= Seq(typesafe_config, base)

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
  log.success("Done building env application.conf")
}

copyResources in Compile := {
  renameEnvConfigTask.value
  (copyResources in Compile).value
}