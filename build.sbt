// the application itself
lazy val app = project
  .in(file("."))
  .settings(
    name := "OSpark",
    scalaVersion := "2.11.12"
  )

// --------------------------------------
// -------- Package ENV Submodules ----------
// --------------------------------------
lazy val devPackage = project
  .in(file("build/dev"))
  .enablePlugins(JavaAppPackaging)
  .settings(
    resourceDirectory in Compile := (resourceDirectory in(app, Compile)).value,
    mappings in Universal += {
      ((resourceDirectory in Compile).value / "dev.conf") -> "conf/application.conf"
    }
  )
  .dependsOn(app)


lazy val testPackage = project
  .in(file("build/test"))
  .enablePlugins(JavaAppPackaging)
  .settings(
    resourceDirectory in Compile := (resourceDirectory in(app, Compile)).value,
    mappings in Universal += {
      ((resourceDirectory in Compile).value / "test.conf") -> "conf/application.conf"
    }
  )
  .dependsOn(app)

lazy val stagePackage = project
  .in(file("build/stage"))
  .enablePlugins(JavaAppPackaging)
  .settings(
    resourceDirectory in Compile := (resourceDirectory in(app, Compile)).value,
    mappings in Universal += {
      ((resourceDirectory in Compile).value / "stage.conf") -> "conf/application.conf"
    }
  )
  .dependsOn(app)

lazy val prodPackage = project
  .in(file("build/prod"))
  .enablePlugins(JavaAppPackaging)
  .settings(
    resourceDirectory in Compile := (resourceDirectory in(app, Compile)).value,
    mappings in Universal += {
      ((resourceDirectory in Compile).value / "prod.conf") -> "conf/application.conf"
    }
  )
  .dependsOn(app)