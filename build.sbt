// dummy value definition to set java library path
//val dummy_java_lib_path_setting = {
//    def JLP = "java.library.path"
//    val jlpv = System.getProperty(JLP)
//    if(!jlpv.contains(";lib"))
//        System.setProperty(JLP, jlpv + ";lib")
//}

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

        // Reflect of Ver2.10.1-> requires to add libraryDependencies explicitly
        //libraryDependencies <+= scalaVersion { "org.scala-lang" % "scala-reflect" % _ },

        // Reflect of Ver2.10.1 requires to add libraryDependencies explicitly
        //libraryDependencies <+= scalaVersion { "org.scala-lang" % "scala-reflect" % _ },

        // add ScalaTest dependency
        //libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"
        //libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test",

        // add typesafe config dependencies
        libraryDependencies += "com.typesafe" % "config" % "1.3.1",

        // add Logback, SLF4j dependencies
        libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3",
        libraryDependencies += "ch.qos.logback" % "logback-core" % "1.2.3",
        libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.25",

        // add HZUtil dependency
        libraryDependencies += "org.hirosezouen" %% "hzutil" % "2.2.0",

        // sbt-native-packager settings
        executableScriptName := "DirectoryLister",
        batScriptExtraDefines += """set "APP_CLASSPATH=%APP_CLASSPATH%;.;conf"""",
        bashScriptExtraDefines += """addJava "-Dlogback.configurationFile=./conf/logback.xml"""",
        mappings in Universal := (mappings in Universal).value filterNot {
            case (_, name) => name.endsWith("~")
        },

        // Avoid sbt warning ([warn] This usage is deprecated and will be removed in sbt 1.0)
        // Current Sbt dose not allow overwrite stabele release created publicLocal task.
        isSnapshot := true,

        // fork new JVM when run and test and use JVM options
        //fork := true,
        //javaOptions += "-Djava.library.path=lib",

        // misc...
        parallelExecution in Test := false,
        //logLevel := Level.Debug,
        scalacOptions += "-deprecation",
        scalacOptions += "-feature",
        scalacOptions += "-Xfatal-warnings"
        //scalacOptions += "-Xlint:unused"
    )

