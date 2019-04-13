name := "AgileBattleship"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.22",
  "com.typesafe.akka" %% "akka-stream" % "2.5.22",
  "com.typesafe.akka" %% "akka-http" % "10.1.8",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.8",
)

// https://mvnrepository.com/artifact/io.cucumber/cucumber-java
libraryDependencies += "io.cucumber" % "cucumber-java" % "4.3.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

libraryDependencies += "com.github.agourlay" %% "cornichon-test-framework" % "0.17.0" % Test
testFrameworks += new TestFramework("com.github.agourlay.cornichon.framework.CornichonFramework")