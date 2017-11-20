
// factor out common settings into a sequence
lazy val commonSettings = Seq(
    organization := "org.hirosezouen",
    version      := "1.0.0",
    scalaVersion := "2.12.4"
)

// sbt-native-packager settings
enablePlugins(JavaAppPackaging)

lazy val root = (project in file(".")).
    settings(commonSettings: _*).
    settings(
        // set the name of the project
        name := "DirectoryLister",

        // Avoid sbt warning ([warn] This usage is deprecated and will be removed in sbt 1.0)
        // Current Sbt dose not allow overwrite stabele release created publicLocal task.
        isSnapshot := true,

        // misc...
        parallelExecution in Test := false,
        //logLevel := Level.Debug,
        scalacOptions += "-deprecation",
        scalacOptions += "-feature",
        scalacOptions += "-Xfatal-warnings"
        //scalacOptions += "-Xlint:unused"
    )

