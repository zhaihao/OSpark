import Dependencies._

// 限制 scala 编译警告信息
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")

// 配置第三方依赖仓库地址
resolvers += "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
resolvers += "spark-packages" at "https://dl.bintray.com/spark-packages/maven"
resolvers += Resolver.url("ooon ivy repo", url("http://repo.ooon.me/release"))(Resolver.ivyStylePatterns)

externalResolvers := Resolver.combineDefaultResolvers(resolvers.value.toVector, mavenCentral = true)

// the application itself
lazy val app = project
  .in(file("."))
  .settings(
    name         := "OSpark",
    scalaVersion := "2.11.12",
    libraryDependencies ++= Seq(
      typesafe_config,
      base
    )
  )

// -------- Package ENV Submodules --------
lazy val devPackage = project.in(file("build/dev"))
  .enablePlugins(JavaAppPackaging)
  .settings(
    resourceDirectory in Compile := (resourceDirectory in (app, Compile)).value,
    mappings in Universal += {
      ((resourceDirectory in Compile).value / "dev.conf") -> "conf/application.conf"
    }
  )
  .dependsOn(app)

lazy val testPackage = project.in(file("build/test"))
  .enablePlugins(JavaAppPackaging)
  .settings(
    resourceDirectory in Compile := (resourceDirectory in (app, Compile)).value,
    mappings in Universal += {
      ((resourceDirectory in Compile).value / "test.conf") -> "conf/application.conf"
    }
  )
  .dependsOn(app)

lazy val stagePackage = project.in(file("build/stage"))
  .enablePlugins(JavaAppPackaging)
  .settings(
    resourceDirectory in Compile := (resourceDirectory in (app, Compile)).value,
    mappings in Universal += {
      ((resourceDirectory in Compile).value / "stage.conf") -> "conf/application.conf"
    }
  )
  .dependsOn(app)

lazy val prodPackage = project.in(file("build/prod"))
  .enablePlugins(JavaAppPackaging)
  .settings(
    resourceDirectory in Compile := (resourceDirectory in (app, Compile)).value,
    mappings in Universal += {
      ((resourceDirectory in Compile).value / "prod.conf") -> "conf/application.conf"
    }
  )
  .dependsOn(app)
