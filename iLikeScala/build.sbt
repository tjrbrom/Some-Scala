name := "iLikeScala"

version := "0.1"

scalaVersion := "2.13.1"

testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-a", "-v", "-s")
parallelExecution in Test := false

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-Xlint",
)

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test
