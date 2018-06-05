
enablePlugins(ScalaJSPlugin)

enablePlugins(ScalaJSBundlerPlugin)

name := "grpc-sjs"

organization := "org.akka-js"

scalaVersion := "2.12.4"

npmDependencies in Compile ++= Seq(
  "grpc" -> "1.10.1"
)

testFrameworks += new TestFramework("utest.runner.Framework")

libraryDependencies ++= Seq(
  "com.lihaoyi" %%% "utest" % "0.6.3" % "test"
)

skip in packageJSDependencies := false
scalaJSUseMainModuleInitializer := true
